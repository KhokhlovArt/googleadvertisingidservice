package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class GoogleAdvertisingIdGetter implements IGoogleAdvertisingIdGetter {
     private String PREFERENCES_DEFAULT_KEY       = "FAKE_GAID";
     private String PREFERENCES_SESSION           = "session_read";
     private String PREFERENCES_SESSION_SELF_READ = "session_self_read";

    private static String getRandomString (int Size) {
        String eng = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String res = "";
        Random r = new Random();
        for(int i = 0; i < Size; i++)
        {
            res += eng.charAt(r.nextInt(eng.length()) );
        }
        return res;
    }

    private String generateDefaultFakeUUID()
    {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHH-mmss-SSS");
        return formatter.format(currentTime) + getRandomString(1) + "-" + getRandomString(4) + "-" + getRandomString(12);
    }

    private boolean saveToDefaultCache(Context cnt, String id)
    {
        try {
            SharedPreferences prefs = cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_WORLD_READABLE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PREFERENCES_DEFAULT_KEY, id);
            editor.commit();
            //cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_WORLD_READABLE).edit().putString(PREFERENCES_DEFAULT_KEY, id).apply();
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    private String getIDFromDefaultCache(Context cnt, String callSource, String callDestination)
    {
        String result = null;
        String session = (!callDestination.toString().equals(cnt.getPackageName().toString()) ) ? PREFERENCES_SESSION : PREFERENCES_SESSION_SELF_READ;
        if (!callDestination.toString().equals(cnt.getPackageName().toString())
                && !callDestination.equals("") && callDestination != null
                && !callSource.equals("") && callSource != null) {
            try {
                Context external_context = cnt.createPackageContext(callDestination, 0);
                result  = external_context.getSharedPreferences(session, Context.MODE_PRIVATE).getString(PREFERENCES_DEFAULT_KEY, null);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (result == null) {
            result = cnt.getSharedPreferences(session, Context.MODE_PRIVATE).getString(PREFERENCES_DEFAULT_KEY, null);
        }
        return result;
    }

    //*****************************************************************************
    //************************ Методы доступные пользователям *********************
    //*****************************************************************************
    @Override
    public String getOriginalID(final Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        String realGAID = "";
        AdvertisingIdClient.Info adInfo = null;
        adInfo = AdvertisingIdClient.getAdvertisingIdInfo(cnt);
        if (adInfo != null){
            String adInfoId = adInfo.getId();
            if ((adInfoId != null) && (!adInfoId.equals("")))
            {
                realGAID = adInfoId;
            }
        }
        return realGAID.equals("") ? null : realGAID;
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter) {
        String result;
        switch (control_parameter) {
            case DEFAULT:
                result = generateDefaultFakeUUID();
                break;
            case GUID_TOOL:
                result = generateDefaultFakeUUID();
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
    public String generateGUID() {
        return generateGUID(GenerateIDType.DEFAULT);
    }

    @Override
    public boolean saveToCache(SaveIDType control_parameter, Context cnt ,String id) {
        boolean result = false;
        switch (control_parameter)
        {
            case DEFAULT:
                result = saveToDefaultCache(cnt, id);
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
    public boolean saveToCache(Context cnt, String id) {
        return saveToCache(SaveIDType.DEFAULT, cnt, id);
    }

    @Override
    public String getIDFromCache(GetIDType control_parameter, Context cnt, String callSource, String callDestination) {
        String result = null;
        switch (control_parameter)
        {
            case DEFAULT:
                result = getIDFromDefaultCache(cnt, callSource, callDestination);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    public String getIDFromCache(Context cnt, String callSource , String callDestination) {
        return getIDFromCache(GetIDType.DEFAULT, cnt, callSource, callDestination);
    }

    @Override
    public String getFakeGaid(final Context cnt, String callSource, String callDestination ) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        String id = null;

        id = getIDFromCache(cnt, callSource, callDestination);
        if (id == null || id.equals(""))
        {
            id = getOriginalID(cnt);
            if (id == null)
            {
                id = generateGUID();
            }
            saveToCache(cnt, id);
        }
        return id;
    }
}
