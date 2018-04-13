package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.DAO.AnotherAppCacheDAO;
import com.advertising_id_service.appclick.googleadvertisingidservice.DAO.SelfCacheDAO;
import com.advertising_id_service.appclick.googleadvertisingidservice.GUID.GUID;
import com.advertising_id_service.appclick.googleadvertisingidservice.IdGenerators.MixIDGenerator;
import com.advertising_id_service.appclick.googleadvertisingidservice.IdGenerators.RandomIDGenerator;
import com.advertising_id_service.appclick.googleadvertisingidservice.IdGenerators.RealGUIDGenerator;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.FilesSearcher;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.IApi;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.RestServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleAdvertisingIdGetter implements IGoogleAdvertisingIdGetter {
    private static final String LOGTAG = "GAIDGetter";
    private List<String> getFilePublisherID(Context cnt, PublisherIDMask mask)
    {
        List files;
        List ids = new ArrayList();
        FilesSearcher fs = new FilesSearcher();
        files = fs.getPublisherFiles(cnt, mask.getPrefix().split(","), mask.getSeporator() ,mask.getExtension().split(","));
        for (Object object : (files)) {
            ids.add(fs.ejectApkGUID(object.toString(), mask.getPrefix(), mask.getSeporator(), mask.getExtension() ));
        }
        return ids;
    }
    private String getInnerPublisherID(Context cnt, String key)
    {
        String result = null;
        try {
            ApplicationInfo ai = cnt.getPackageManager().getApplicationInfo(cnt.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            result = bundle.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NullPointerException e) {
        }
        return result ;
    }

    private boolean saveToCache(SaveIDType control_parameter, Context cnt ,String id) {
        boolean result = false;
        switch (control_parameter) {
            case DEFAULT:
                result = new SelfCacheDAO().setContext(cnt).save(new GUID(id));
                break;
            case SELF_CASHE:
                result = new SelfCacheDAO().setContext(cnt).save(new GUID(id));
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
    private boolean saveToCache(Context cnt, String id) {
        return saveToCache(SaveIDType.DEFAULT, cnt, id);
    }


    private String getIDFromCache(GetIDType control_parameter, Context cnt, String callSource, String callDestination) {
        String result = null;
        switch (control_parameter)
        {
            case DEFAULT:
                result = new SelfCacheDAO().setContext(cnt).get().getId();
                break;
            case ANOTHERAPPCACHE:
                AnotherAppCacheDAO dao = new AnotherAppCacheDAO();
                dao.setContext(cnt);
                dao.setCallSource(callSource).setCallDestination(callDestination);
                result = dao.get().getId();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
    private String getIDFromCache(Context cnt, String callSource , String callDestination) {
        return getIDFromCache(GetIDType.DEFAULT, cnt, callSource, callDestination);
    }
    private  boolean cheekPermisons(Context cnt)
    {
        return ((ActivityCompat.checkSelfPermission(cnt.getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)&&
            (ActivityCompat.checkSelfPermission(cnt.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED));
    }
    //*****************************************************************************
    //************************ Методы доступные пользователям *********************
    //*****************************************************************************
    @Override
    public String getOriginalID(final Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        String realGAID = "";
        AdvertisingIdClient.Info adInfo = null;
        adInfo = AdvertisingIdClient.getAdvertisingIdInfo(cnt);
        if (adInfo != null){
            String adInfoId = adInfo.getId();
            if ((adInfoId != null) && (!adInfoId.equals("")))
            {
                realGAID = adInfoId;
            }
        }
        return realGAID.equals("") ? null : realGAID;
    }

    @Override
    public String getFakeGaid(final Context cnt /*, String callSource, String callDestination*/ ) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        return generateGUID(GenerateIDType.MIX, cnt);
// // Если надо делать проверку на наличие закэшированного ID или наличия оригинального
//        String id = null;
//        id = getIDFromCache(cnt, "", ""); //Вначале смотрим в локальном кэше
//        if (id == null || id.equals(""))
//        {
//            id = getIDFromCache(GetIDType.ANOTHERAPPCACHE, cnt, callSource, callDestination); //если в локальном кэше пусто, тогда смотрим в кэше приложения callDestination
//            if (id == null || id.equals("")) {
//                try {
//                    id = getOriginalID(cnt);
//                }catch (Exception e){
//                }
//            }
//            if (id == null || id.equals("")) {
//                id = generateGUID(cnt);
//            }
//            saveToCache(cnt, id);
//        }
//        return id;
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        String result;
        switch (control_parameter) {
            case DEFAULT:
                result = new RandomIDGenerator().generateId().getId();
                break;
            case MIX:
                result = new MixIDGenerator().setContext(cnt).generateId().getId();
                break;
            case GUID_TOOL:
                result = new RandomIDGenerator().generateId().getId();
                break;
            case REAL_GUID:
                result = new RealGUIDGenerator().generateId().getId();
                break;
            case RANDOM:
                result = new RandomIDGenerator().generateId().getId();
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
    public String generateGUID(Context cnt) {
        return generateGUID(GenerateIDType.DEFAULT, cnt);
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        List result = new ArrayList();
        switch (control_parameter)
        {
            case DEFAULT:
                result = getFilePublisherID(cnt, mask);
                break;
            case FROM_FILE:
                result = getFilePublisherID(cnt, mask);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
    public List<String> getFilePublisherIDs(Context cnt, PublisherIDMask mask) {
        return getFilePublisherIDs(PublusherIDType.DEFAULT, cnt, mask);
    }

    @Override
    public String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key) {
        String result;
        switch (control_parameter)
        {
            case DEFAULT:
                result = getInnerPublisherID(cnt, key);
                break;
            case INNER:
                result = getInnerPublisherID(cnt, key);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    @Override
    public String getGAID(Context cnt, String callDestination)  throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {

        new InstallationInfo().saveDateFirstStart(cnt); // сохраняем время первого запуска(запроса guid-а)

        String id = null;
        String callSource = cnt.getPackageName();
        if (callDestination.equals("") || callDestination == null) {
            id = getIDFromCache(cnt, "", ""); //Вначале смотрим в локальном кэше
            if (id == null || id.equals("")) {
                //id = getIDFromCache(GetIDType.ANOTHERAPPCACHE, cnt, callSource, callDestination); //если в локальном кэше пусто, тогда смотрим в кэше приложения callDestination
                if (id == null || id.equals("")) {
                    try {
                        id = getOriginalID(cnt);
                    } catch (Exception e) {
                        Log.e("GAIDGetter ", e.getMessage() );
                    }
                }
                if (id == null || id.equals("")) {
                    id = generateGUID(cnt);
                }
                saveToCache(cnt, id);
            }
        }
        else
        {
            id = getIDFromCache(GetIDType.ANOTHERAPPCACHE, cnt, callSource, callDestination);
            if (id == null || id.equals("")) {
                id = getGAID(cnt, "");
            }
        }
        return id;
    }

    public String getInnerPublisherIDs(Context cnt, String key) {
        return getInnerPublisherIDs(PublusherIDType.DEFAULT, cnt, key);
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************

    //Метод добавляет в базу информацию по устрйоству(его GAID-у). В базе создаётся запись c DeviceInfo.guid
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    public void rest_create(final Context cnt, LoaderManager lm, String callDestination, String login, String pass)
    {
        if (cheekPermisons(cnt)) {
            GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
            RestServicer.getRestServicer().create(cnt, lm, callDestination, login, pass);
        }else{
            Log.e(LOGTAG, "Has not INTERNET or READ_PHONE_STATE permission");
        }
    }

    //Метод добавляет информацию по конкретной инсталяции. InstallationInfo
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param installInfo     - объект содержащий информацию по инсталяции
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    //
    // Пример использования:
    // GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
    // InstallationInfo install_info = new InstallationInfo(getApplicationContext(),"",  new PublisherIDMask("GooGames,AppLandGames,AppClickGames,gameclub", "_", ".zip,.apk"));
    // g.rest_install(getApplicationContext(), getSupportLoaderManager(), "", install_info);
    //
    public void rest_install(final Context cnt, LoaderManager lm, String callDestination, InstallationInfo installInfo, String login, String pass)
    {
        if (cheekPermisons(cnt)) {
           GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
           RestServicer.getRestServicer().install(cnt, lm, callDestination, installInfo, login, pass);
        }else{
            Log.e(LOGTAG, "Has not INTERNET or READ_PHONE_STATE permission");
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param readType        - по какому из параемтров искать в базе (GAID, imei, imsi...)
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    public ResultRead rest_read(final Context cnt, LoaderManager lm, IApi.RestReadType readType, String callDestination, String login, String pass)
    {
        if (cheekPermisons(cnt)) {
            GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
            return RestServicer.getRestServicer().read(cnt, lm, readType, callDestination, login, pass);
        }else{
            Log.e(LOGTAG, "Has not INTERNET or READ_PHONE_STATE permission");
            return null;
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    public void rest_delete(final Context cnt, LoaderManager lm, String callDestination, String login, String pass)
    {
        if (cheekPermisons(cnt)) {
            GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
            RestServicer.getRestServicer().delete(cnt, lm, callDestination, login, pass);
        }else{
            Log.e(LOGTAG, "Has not INTERNET or READ_PHONE_STATE permission");
        }
    }

    //Метод отпроавляет обновление информации по устрйоству(его GAID-у).
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    public void rest_update(final Context cnt, LoaderManager lm, String callDestination, String login, String pass)
    {
        if (cheekPermisons(cnt)) {
            GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
            RestServicer.getRestServicer().update(cnt, lm, callDestination, login, pass);
        }else{
            Log.e(LOGTAG, "Has not INTERNET or READ_PHONE_STATE permission");
        }
    }

}