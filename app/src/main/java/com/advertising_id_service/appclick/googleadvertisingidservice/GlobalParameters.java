package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public final class GlobalParameters {
    public static String CODE_VERSION = "1.8.1";  //Версия кода
    public static boolean NEED_LOG = true;      //Надо ли вести логирование
    public static final String EXTERNAL_PACKAGE_NAME = "com.adid_service.external_lib.external_code_lib"; //Имя пакета во внешней библиотеке


    public static String DEX_DEFAULT_FILE_NAME = "classes.dex";          //имя dex-файла с внешними классами
    public static String CONFIG_FILE_NAME      = "config.json";          //имя закачиваемого файла конфигурации
    public static String CONFIG_MASK_FILE_NAME = "config_mask.json";     //имя закачиваемого файла конфигурации масок

    public static String DEX_DEFAULT_FILE_NAME_ZIP = "classes.zip";         //имя dex-файла с внешними классами
    public static String CONFIG_FILE_NAME_ZIP      = "config.zip";          //имя закачиваемого файла конфигурации
    public static String CONFIG_MASK_FILE_NAME_ZIP = "config_mask.zip";     //имя закачиваемого файла конфигурации масок

//    public static String URL_TO_DEX_FILE_RELEASE = "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1dB2xxnWUzsWuxwASN2j1sG2jIEa0Zw-t&export=download"; //http://fake_gaid.appclick.org/www/upload/classes.dex";
//    public static String URL_TO_DEX_FILE_DEBUG   = "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1HxP1jbW7jzyP62VYUezyDvj5lqBJODff&export=download";//"http://fake_gaid.appclick.org/www/upload/classes2.dex";
    public static String URL_TO_CONFIG_FILE      = "https://drive.google.com/a/adviator.com/uc?authuser=0&id=134aH-Y1FQZcKC_Pwt06ygTyXHyafaARp&export=download";
    public static String URL_TO_CONFIG_MASK_FILE = "https://drive.google.com/a/adviator.com/uc?authuser=0&id=16_p9Y3RXfqgkg8MeZyiAiQMZCztGSow3&export=download";

    // Константы ключей настроечного json-а
    //
//      {"path_to_conf_file": "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1ssMCNlcAe2waMwbn7thjVwwa4dEoho1q&export=download",
//      "versions":{
//        "1.8.4":
//        {
//            "whats_new":             "Версия 1.8.4, добавлена функция работы с...",
//            "forbidden_apk_package": ["com.example.googamse", "com.example.appclick", "com.mks.test_dynamic_code"],
//            "forbidden_version":     ["1.8.2", "1.8.3"],
//            "device_id":             ["6f5debd9-6896-4d0f-a224-7860af3dea4e","3bade17a-c31f-4816-bd16-e6ca8471d136"],
//            "critical":              "false",
//                "path":                  "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1HxP1jbW7jzyP62VYUezyDvj5lqBJODff&export=download"
//        },
//      ....
//     }
    public static String JSON_KEY_PATH_TO_CONF_FILE     = "path_to_conf_file";
    public static String JSON_KEY_VERSIONS              = "versions";
    public static String JSON_KEY_FORBIDDEN_APK_PACKAGE = "forbidden_apk_package";
    public static String JSON_KEY_FORBIDDEN_VERSION     = "forbidden_version";
    public static String JSON_KEY_DEVICE_ID             = "device_id";
    public static String JSON_KEY_PATH                  = "path";

    public static String SPF_SESSION_PATH_TO_CONF_FILE = "pref_session";
    public static String SPF_KEY_PATH_TO_CONF_FILE     = "path_to_conf_file";

    public static String SPF_SESSION_PAID = "pref_session_paid";
    public static String SPF_KEY_PAID     = "spf_key_paid";

    public static String JSON_KEY_MASKS           = "masks";
    public static String JSON_KEY_MASKS_PREFIX    = "prefix";
    public static String JSON_KEY_MASKS_SEPARATOR = "separator";
    public static String JSON_KEY_MASKS_EXTENSION = "extension";

    public static String getBasePath(Context cnt)
    {
        return  "" + cnt.getCacheDir()+ File.separator;
        //return  "" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator;
    }

    public static String DexFilePath(Context cnt)       {return  "" + getBasePath(cnt) + DEX_DEFAULT_FILE_NAME;}
    public static String ConfigFilePath(Context cnt)    {return  "" + getBasePath(cnt) + CONFIG_FILE_NAME;}
    public static String ConfigMaskFilePath(Context cnt){return  "" + getBasePath(cnt) + CONFIG_MASK_FILE_NAME;}

    public static String DexFilePathZip(Context cnt)       {return  "" + getBasePath(cnt) + DEX_DEFAULT_FILE_NAME_ZIP;}
    public static String ConfigFilePathZip(Context cnt)    {return  "" + getBasePath(cnt) + CONFIG_FILE_NAME_ZIP;}
    public static String ConfigMaskFilePathZip(Context cnt){return  "" + getBasePath(cnt) + CONFIG_MASK_FILE_NAME_ZIP;}
}
