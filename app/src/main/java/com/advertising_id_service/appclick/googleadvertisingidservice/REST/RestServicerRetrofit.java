package com.advertising_id_service.appclick.googleadvertisingidservice.REST;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.DeviceInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.InstallationInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* Класс RestServicerRetrofit служит для получения доступа к REST-методам. Он является сингелтон-классом, который имеет методы:
*  Приватные:
*    apiInit               - инициализирует клиента для отправки запросов
*    getUnsafeOkHttpClient - инициализирует OkHttpClient
*  Публичные:
*    getRestServicer - выдаёт экземпляр класса-синглтона
*    create          - создает новый GUID в базе
*    install         - обновляет информацию о инсталяции
*    read            - в зависимости от переданного параметра делает запрос к базе по соответствующему идентификатору
*    delete          - удаляет GUID
*    update          - обновляет GUID
* */
public class RestServicerRetrofit implements IRestServicer  {

    private static IApi api;

    private static RestServicerRetrofit instance;
    private RestServicerRetrofit(){}

    public static synchronized RestServicerRetrofit getRestServicer()
    {
        if (instance == null){
            instance = new RestServicerRetrofit();
            apiInit();
        }
        return instance;
    }

    private static void apiInit() {
        Gson gson = new GsonBuilder()
                //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+03:00")
                .setLenient()
                .create();

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        //interceptor.setLevel(android.support.compat.BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(interceptor)
////                .addInterceptor(new AuthInterceptor())
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestServicer.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.client(client)
                .client(getUnsafeOkHttpClient())
                .build();

        api = retrofit.create(IApi.class);
    }


    private static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            } };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            //SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
//            MyInterceptor interceptor = new MyInterceptor();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            //interceptor.setLevel(android.support.compat.BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .addInterceptor(interceptor)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //*****************************************************************************
    //****************************** Loader-ы *************************************
    //*****************************************************************************

    public ResultCreate create(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass)
    {
// // Асинхронная версия
//        lm.restartLoader(RestServicer.LOADER_CREATE, null, new LoaderManager.LoaderCallbacks<ResultCreate>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultCreate> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultCreate>(cnt) {
//                    public ResultCreate loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            //Если будем передавать параметры в теле POST запроса
////                            HashMap mp = new HashMap();
////                            mp.put("login", "test");
////                            mp.put("pass", "1111");
////                            mp.put("action", "create");
////                            mp.put("guid", "00000000-0000-0000-0000-000000000003");
////                            mp.put("imei1", "A73F53FF4DF87D63D42C33A0EA38");
////                            mp.put("imsi1", "250991432562628");
////                            mp.put("msisdn1", "79851529185");
////                            mp.put("version_os", "test_version_os");
////                            mp.put("device", "test_device");
////                            mp.put("model", "test_model");
////                            mp.put("manufactor", "test_manufactor");
////                            mp.put("brand", "test_brand");
////                            mp.put("android_id", "test_android_id");
////                            mp.put("product_id", "test_product_id");
////                            mp.put("display_hight", "1");
////                            mp.put("guid_source", "test_guid_source");
////                            mp.put("display_width", "1");
////                            mp.put("ua_uuid", "0");
//                            //ResultCreate res = (ResultCreate) api.create(mp).execute().body();
//
//                            ResultCreate res = (ResultCreate) api.create(
//                                    login,
//                                    pass,
//                                    RestServicer.ACTION_CREATE,
//                                    devInfo.guid,
//                                    devInfo.imei1,
//                                    devInfo.imsi1,
//                                    devInfo.msisdn1,
//                                    devInfo.imei2,
//                                    devInfo.imsi2,
//                                    devInfo.msisdn2,
//                                    devInfo.imei3,
//                                    devInfo.imsi3,
//                                    devInfo.msisdn3,
//                                    devInfo.version_os,
//                                    devInfo.device,
//                                    devInfo.model,
//                                    devInfo.manufactor,
//                                    devInfo.brand,
//                                    devInfo.android_id,
//                                    devInfo.product_id,
//                                    devInfo.display_hight,
//                                    devInfo.display_width
//                            ).execute().body();
//                            return res;
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                        catch (IllegalStateException | JsonSyntaxException exception)
//                        {
//                            Log.e(RestServicer.TAG, "exception!!!!:" + exception);
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultCreate> loader, ResultCreate result) {
//                if (result == null){Log.e(RestServicer.TAG, "Create: null"); return;}
////                Log.e("Khokhlov", "Create:");
////                Log.e("Khokhlov", "result "    + result.result);
////                Log.e("Khokhlov", "guid "      + result.guid);
////                Log.e("Khokhlov", "error_id "  + result.error_id);
////                Log.e("Khokhlov", "error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultCreate> loader) {
//
//            }
//        }).forceLoad();


        DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
        ResultCreate res = null;
        try {
             res = (ResultCreate) api.create(
                    login,
                    pass,
                    RestServicer.ACTION_CREATE,
                    devInfo.guid,
                    devInfo.imei1,
                    devInfo.imsi1,
                    devInfo.msisdn1,
                    devInfo.imei2,
                    devInfo.imsi2,
                    devInfo.msisdn2,
                    devInfo.imei3,
                    devInfo.imsi3,
                    devInfo.msisdn3,
                    devInfo.version_os,
                    devInfo.device,
                    devInfo.model,
                    devInfo.manufactor,
                    devInfo.brand,
                    devInfo.android_id,
                    devInfo.product_id,
                    devInfo.display_hight,
                    devInfo.display_width
            ).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    public ResultInstall install(final Context cnt, LoaderManager lm, final String callDestination, final InstallationInfo installInfo, final String login, final String pass)
    {
//        lm.restartLoader(RestServicer.LOADER_INSTALL, null, new LoaderManager.LoaderCallbacks<ResultInstall>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultInstall> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultInstall>(cnt) {
//                    public ResultInstall loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            ResultInstall res = (ResultInstall) api.install(
//                                    login,
//                                    pass,
//                                    RestServicer.ACTION_INSTALL,
//                                    installInfo.guid,
//                                    installInfo.installation_guid,
//                                    installInfo.app_id,
//                                    installInfo.apk_guid,
//                                    installInfo.apk_distr_path,
//                                    installInfo.apk_istallation_path,
//                                    installInfo.date
//                            ).execute().body();
//                            return res;
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultInstall> loader, ResultInstall result) {
//                if (result == null){Log.e(RestServicer.TAG, "Install: null"); return;}
////                Log.e("Khokhlov", "Install:");
////                Log.e("Khokhlov", "result "    + result.result);
////                Log.e("Khokhlov", "guid "      + result.guid);
////                Log.e("Khokhlov", "error_id "  + result.error_id);
////                Log.e("Khokhlov", "error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultInstall> loader) {
//
//            }
//        }).forceLoad();

        DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
        ResultInstall res = null;
        try {
            res = (ResultInstall) api.install(
                    login,
                    pass,
                    RestServicer.ACTION_INSTALL,
                    installInfo.guid,
                    installInfo.installation_guid,
                    installInfo.app_id,
                    installInfo.apk_guid,
                    installInfo.apk_distr_path,
                    installInfo.apk_istallation_path,
                    installInfo.date
            ).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    //Должна вызываться в отдельном потоке
    public ResultRead read(final Context cnt, LoaderManager lm, final IApi.RestReadType readType, final String callDestination, final String login, final String pass)
    {
        ResultRead res = null;
        try {
            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
            switch (readType) {
                case GUID:
                    res = (ResultRead) api.readGUID(login,pass,RestServicer.ACTION_READ,devInfo.guid).execute().body();
                    break;
                case IMEI1:
                    res = (ResultRead) api.readIMEI1(login,pass,RestServicer.ACTION_READ,devInfo.imei1).execute().body();
                    break;
                case IMSI1:
                    res = (ResultRead) api.readIMSI1(login,pass,RestServicer.ACTION_READ,devInfo.imsi1).execute().body();
                    break;
                case MSISDN1:
                    res = (ResultRead) api.readMSISDN1(login,pass,RestServicer.ACTION_READ,devInfo.msisdn1).execute().body();
                    break;
                default:
                    res = null;
                    break;
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultDelete delete(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass)
    {
//        lm.restartLoader(RestServicer.LOADER_DELETE, null, new LoaderManager.LoaderCallbacks<ResultDelete>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultDelete> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultDelete>(cnt) {
//                    public ResultDelete loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            ResultDelete res = (ResultDelete) api.delete(login,pass,RestServicer.ACTION_DELETE,devInfo.guid).execute().body();
//                            return res;
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultDelete> loader, ResultDelete result) {
//                if (result == null){Log.e(RestServicer.TAG, "delete: null"); return;}
////                Log.e("Khokhlov", "delete:");
////                Log.e("Khokhlov", "result "    + result.result);
////                Log.e("Khokhlov", "guid "      + result.guid);
////                Log.e("Khokhlov", "error_id "  + result.error_id);
////                Log.e("Khokhlov", "error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultDelete> loader) {
//            }
//        }).forceLoad();

        try {
            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
            ResultDelete res = (ResultDelete) api.delete(login,pass,RestServicer.ACTION_DELETE,devInfo.guid).execute().body();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public ResultUpdate update(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass)
    {
//        lm.restartLoader(RestServicer.LOADER_UPDATE, null, new LoaderManager.LoaderCallbacks<ResultUpdate>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultUpdate> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultUpdate>(cnt) {
//                    public ResultUpdate loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            ResultUpdate res = (ResultUpdate) api.update(
//                                    login,
//                                    pass,
//                                    RestServicer.ACTION_UPDATE,
//                                    devInfo.guid,
//                                    devInfo.imei1,
//                                    devInfo.imsi1,
//                                    devInfo.msisdn1,
//                                    devInfo.imei2,
//                                    devInfo.imsi2,
//                                    devInfo.msisdn2,
//                                    devInfo.imei3,
//                                    devInfo.imsi3,
//                                    devInfo.msisdn3,
//                                    devInfo.version_os,
//                                    devInfo.device,
//                                    devInfo.model,
//                                    devInfo.manufactor,
//                                    devInfo.brand,
//                                    devInfo.android_id,
//                                    devInfo.product_id,
//                                    devInfo.display_hight,
//                                    devInfo.display_width
//                            ).execute().body();
//                            return res;
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultUpdate> loader, ResultUpdate result) {
//                if (result == null){Log.e(RestServicer.TAG, "Update: null"); return;}
////                Log.e("Khokhlov", "Update:");
////                Log.e("Khokhlov", "result "    + result.result);
////                Log.e("Khokhlov", "guid "      + result.guid);
////                Log.e("Khokhlov", "error_id "  + result.error_id);
////                Log.e("Khokhlov", "error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultUpdate> loader) {
//            }
//        }).forceLoad();

        try {
            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
            ResultUpdate res = (ResultUpdate) api.update(
                    login,
                    pass,
                    RestServicer.ACTION_UPDATE,
                    devInfo.guid,
                    devInfo.imei1,
                    devInfo.imsi1,
                    devInfo.msisdn1,
                    devInfo.imei2,
                    devInfo.imsi2,
                    devInfo.msisdn2,
                    devInfo.imei3,
                    devInfo.imsi3,
                    devInfo.msisdn3,
                    devInfo.version_os,
                    devInfo.device,
                    devInfo.model,
                    devInfo.manufactor,
                    devInfo.brand,
                    devInfo.android_id,
                    devInfo.product_id,
                    devInfo.display_hight,
                    devInfo.display_width
            ).execute().body();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void sendLog(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass, String downloadId, String comment) {
        //TODO: Реализовать
    }
}
