package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.Context.TELEPHONY_SERVICE;

//Класс - синглтон. Содержит в себе информацию об устройстве.
// Имеет следующие методы:
//  Публичные:
//    getDeviceInfo    - выдаёт экземпляр класса-синглтона
//    updateDeviceInfo - обновляет все атрибуты из текущих параметров устройства
//  Публичные:
//    getХХХ, где ХХХ - имя параметра. Методы получающие тот или иной параметр
//    updateIMSI_IMEI - метод обновляющий параметры IMSI1/2/3 и IMEI1/2/3
public class DeviceInfo {
    public static String guid;
    public static String imei1;
    public static String imsi1;
    public static String msisdn1;

    public static String imei2;
    public static String imsi2;
    public static String msisdn2;

    public static String imei3;
    public static String imsi3;
    public static String msisdn3;

    public static String version_os;
    public static String device;
    public static String model;
    public static String manufactor;
    public static String brand;
    public static String android_id;
    public static String product_id;
    public static String display_hight;
    public static String display_width;

    public static String versionName;
    //public String sign;
    private static DeviceInfo instance;

    public static synchronized DeviceInfo getDeviceInfo(Context cnt, String callDestination)
    {
        if (instance == null){
            System.loadLibrary("hello-jni2");
            instance = new DeviceInfo(cnt, callDestination);
        }
        updateDeviceInfo(cnt, callDestination); //Каждый раз обновляем информацию об устрйостве, т.к. она может меняться во время использования приложения с библиотекой
        return instance;
    }

    private DeviceInfo(Context cnt, String callDestination) {
        //updateDeviceInfo(cnt, callDestination);
    }

    public static void updateDeviceInfo(Context cnt, String callDestination)
    {
        guid          = getGuid(cnt, callDestination);
//        imei1         = getImei1(cnt);
//        imsi1         = getImsi1(cnt);
        msisdn1       = getMsisdn1(cnt);

//        imei2         = getImei2(cnt);
//        imsi2         = getImsi2(cnt);
        msisdn2       = getMsisdn2(cnt);

//        imei3         = getImei3(cnt);
//        imsi3         = getImsi3(cnt);
        msisdn3       = getMsisdn3(cnt);

        version_os    = getVersionOs();
        device        = getDevice();
        model         = getModel();
        manufactor    = getManufactor();
        brand         = getBrand();
        android_id    = getAndroidId(cnt);
        product_id    = getProductId();
        display_hight = getDisplayHight(cnt);
        display_width = getDisplayWidth(cnt);

        versionName = getVersionName(cnt);
        //sign        = getSign(cnt);
        updateIMSI_IMEI(cnt);
    }

    private static String getGuid(Context cnt, String callDestination) {
        String result = null;
        try {
            result = new GoogleAdvertisingIdGetter().getGAID(cnt, callDestination);
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        return result;
    }
    private String getImei1(Context context) {
        String result = null;
        if (context != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            } else {
                if (telephonyManager != null) {
                    result = telephonyManager.getDeviceId();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        result = telephonyManager.getPhoneCount() >= 1 ? telephonyManager.getDeviceId(1) : null;
                    }
                }
            }
        }
        return result;
    }

    private String getImsi1(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return mTelephonyMgr.getSubscriberId();
        }
        return null;
    }

    private static String getMsisdn1(Context context) {
        TelephonyManager tMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return tMgr.getLine1Number();
        }
        return null;
    }


    private String getImei2(Context context){
        String devicIMEI2 = null;
            if (context != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            } else {
                devicIMEI2 = telephonyManager.getDeviceId();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    devicIMEI2 = telephonyManager.getPhoneCount() >= 2  ? telephonyManager.getDeviceId(2) : null;
                }
            }
        }
        return devicIMEI2;
    }

    private String getImsi2(Context context) {
        String imsi = null;
//        try {
//            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
//            Method getSubId = TelephonyManager.class.getMethod("getSubscriberId", int.class);
//            SubscriptionManager sm = (SubscriptionManager) context.getSystemService(context.TELEPHONY_SUBSCRIPTION_SERVICE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//                if (sm.getActiveSubscriptionInfoForSimSlotIndex(1) != null) {
//                    imsi = (String) getSubId.invoke(tm, sm.getActiveSubscriptionInfoForSimSlotIndex(1).getSubscriptionId()); // Sim slot 2 IMSI
//                }
//            }
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
        return imsi;
    }

    private static String getMsisdn2(Context context)
    {
        return null;
    }

    private String getImei3(Context context){
        String devicIMEI3 = null;
        if (context != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            } else {
                devicIMEI3 = telephonyManager.getDeviceId();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    devicIMEI3 = telephonyManager.getPhoneCount() >= 3  ? telephonyManager.getDeviceId(3) : null;
                }
            }
        }
        return devicIMEI3;
    }

    private String getImsi3(Context context) {
        String imsi = null;
//        try {
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
//            Method getSubId = TelephonyManager.class.getMethod("getSubscriberId", int.class);
//            SubscriptionManager sm = (SubscriptionManager) context.getSystemService(context.TELEPHONY_SUBSCRIPTION_SERVICE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//                if (sm.getActiveSubscriptionInfoForSimSlotIndex(1) != null) {
//                    imsi = (String) getSubId.invoke(tm, sm.getActiveSubscriptionInfoForSimSlotIndex(2).getSubscriptionId()); // Sim slot 3 IMSI
//                }
//            }
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
        return imsi;
    }

    private static String getMsisdn3(Context context)
    {
        return null;
    }


    private static String getVersionOs()
    {
        return "" + Build.VERSION.RELEASE;
    }

    private static String getDevice(){return "" + Build.DEVICE;}

    private static String getModel(){return "" + Build.MODEL;}

    private static String getManufactor(){return "" + Build.MANUFACTURER;}

    private static String getBrand(){return "" + Build.BRAND;}

    private static String getAndroidId(Context context){return (context != null) ? Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID) : null;}

    private static String getProductId(){return "" + Build.PRODUCT;}

    private static String getDisplayHight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return "" + size.y;
    }

    private static String getDisplayWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return "" + size.x;
    }




    private static String getDeviceIdBySlot(Context context, String predictedMethodName, int slotID) {

        String imei = null;

        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        try {

            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());

            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimID = telephonyClass.getMethod(predictedMethodName, parameter);

            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object ob_phone = getSimID.invoke(telephony, obParameter);

            if (ob_phone != null) {
                imei = ob_phone.toString();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return imei;
    }

    //Метод устанавливает imei1,2,3 и imsi1,2,3
    private static void updateIMSI_IMEI(Context context)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);

                if (subscriptionManager.getActiveSubscriptionInfoCount() == 0) {
                    imei1 = telephonyManager.getDeviceId();
                } else if (subscriptionManager.getActiveSubscriptionInfoCount() == 1) {
                    if (SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(0) != null) {
                        int subscriptionId1 = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(0).getSubscriptionId();
                        try {
                            Class c = Class.forName("android.telephony.TelephonyManager");
                            Method m = c.getMethod("getSubscriberId", int.class);
                            Object o = m.invoke(telephonyManager, subscriptionId1);
                            imsi1 = (String) o;
                            imei1 = getDeviceIdBySlot(context, "getDeviceId", 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (subscriptionManager.getActiveSubscriptionInfoCount() == 2) {

                    int subscriptionId1 = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(0).getSubscriptionId();
                    int subscriptionId2 = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(1).getSubscriptionId();

                    try {
                        Class c = Class.forName("android.telephony.TelephonyManager");
                        Method m = c.getMethod("getSubscriberId", int.class);
                        Object o = m.invoke(telephonyManager, subscriptionId1);

                        imsi1 = (String) o;
                        o = m.invoke(telephonyManager, subscriptionId2);
                        imsi2 = (String) o;

                        m = c.getMethod("getDeviceId", int.class);
                        o = m.invoke(telephonyManager, subscriptionId1);
                        imei1 = (String) o;

                        o = m.invoke(telephonyManager, subscriptionId2);
                        imei2 = (String) o;

                        imei1 = getDeviceIdBySlot(context, "getDeviceId", 0);
                        imei2 = getDeviceIdBySlot(context, "getDeviceId", 1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    int subscriptionId1 = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(0).getSubscriptionId();
                    int subscriptionId2 = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(1).getSubscriptionId();
                    int subscriptionId3 = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(2).getSubscriptionId();

                    try {
                        Class c = Class.forName("android.telephony.TelephonyManager");
                        Method m = c.getMethod("getSubscriberId", int.class);

                        Object o = m.invoke(telephonyManager, subscriptionId1);
                        imsi1 = (String) o;
                        o = m.invoke(telephonyManager, subscriptionId2);
                        imsi2 = (String) o;
                        o = m.invoke(telephonyManager, subscriptionId3);
                        imsi3 = (String) o;

                        m = c.getMethod("getDeviceId", int.class);
                        o = m.invoke(telephonyManager, subscriptionId1);
                        imei1 = (String) o;
                        o = m.invoke(telephonyManager, subscriptionId2);
                        imei2 = (String) o;
                        o = m.invoke(telephonyManager, subscriptionId3);
                        imei3 = (String) o;

                        imei1 = getDeviceIdBySlot(context, "getDeviceId", 0);
                        imei2 = getDeviceIdBySlot(context, "getDeviceId", 1);
                        imei3 = getDeviceIdBySlot(context, "getDeviceId", 2);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else {
            }
        }
    }
    private static String getVersionName(Context cnt) {
        try {
            return cnt.getPackageManager().getPackageInfo(cnt.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//  Пока не используем
//    public static native String getSig(Context cnt);
//    public  String NdkGetSign(Context cnt)
//    {
//        return getSig(cnt);
//    }
//
//    private String getSign(Context cnt)
//    {
//        return NdkGetSign(cnt);
//    }
}


