package com.advertising_id_service.appclick.googleadvertisingidservice.CryptoProvider;

import android.content.Context;
import android.content.pm.PackageManager;

public class CryptoProviderServicer {

    private static final String HEX = "0123456789ABCDEF";
    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2*buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    public static String getSig(Context cnt)
    {
        android.content.pm.Signature[] sigs = new android.content.pm.Signature[0];
        try {
            sigs = cnt.getPackageManager().getPackageInfo(cnt.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
            if ((sigs.length > 0) && (sigs != null))
            {
                return toHex(sigs[0].toByteArray());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String cript(String val)
    {
        return codeFromJNI(val);
    }

    public static String deCript(String val)
    {
        return decodeFromJNI(val);
    }
    public static String codeUrlParams(Context cnt, String str){return  codeUrlParamsJNI(cnt, str);}

    public static native String codeFromJNI(String str);
    public static native String decodeFromJNI(String str);


    public static native String codeUrlParamsJNI(Context cnt, String str);

    static {
        System.loadLibrary("hello-jni");
    }
}
