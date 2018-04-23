package com.advertising_id_service.appclick.googleadvertisingidservice.Logger;


import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;

public class Logger implements ILogger {
    static String TAG = "GAIDGetter";

    public static void log(String msg) {
        if (GlobalParameters.NEED_LOG)
        {
            Log.d(TAG,"v " + GlobalParameters.CODE_VERSION + ": " + msg);
        }
    }
}
