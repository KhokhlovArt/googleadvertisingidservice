package com.advertising_id_service.appclick.googleadvertisingidservice.Logger;


import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;

//Собственный класс для логирования.
public class Logger implements ILogger {
    static String TAG = "GAIDGetter";

    //При логировании выводит версию библиотеки, полезно для определения из какой же версии был вызван тот или иной метод.
    public static void log(String msg) {
        if (GlobalParameters.NEED_LOG)
        {
            Log.d(TAG,"v " + GlobalParameters.CODE_VERSION + ": " + msg);
        }
    }
}
