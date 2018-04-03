package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GoogleAdvertisingIdGetter implements IGoogleAdvertisingIdGetter {
     private String PREFERENCES_DEFAULT_KEY       = "FAKE_GAID";
     private String PREFERENCES_SESSION           = "session_read";

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
            cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).edit().putString(PREFERENCES_DEFAULT_KEY, id).apply();
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
        result = cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).getString(PREFERENCES_DEFAULT_KEY, null);
        return result;
    }

    private String getGAIDFromAnotherAppCache(Context cnt, String callSource, String callDestination)
    {
        String result = null;
        if (!callDestination.toString().equals(cnt.getPackageName().toString())
                && !callDestination.equals("") && callDestination != null
                && !callSource.equals("") && callSource != null
                && !callSource.equals(callDestination)) {
            Uri CONTACT_URI = Uri.parse(LibContentProvider.QUERY_URI + "/" + callDestination);
            Cursor cursor = null;
            try {
                cursor = cnt.getContentResolver().query(CONTACT_URI, null, null, null, null);
                int firstNameColIndex = cursor.getColumnIndex(LibContentProvider.GAID_COL_NAME);
                while (cursor.moveToNext()) {
                    result = cursor.getString(firstNameColIndex);
                }
            }
            catch (Exception e){
            }
            finally {
                if(cursor != null) {
                    cursor.close();
                }
            }
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
            case ANOTHERAPPCACHE:
                result = getGAIDFromAnotherAppCache(cnt, callSource, callDestination);
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
        id = getIDFromCache(cnt, "", ""); //Вначале смотрим в локальном кэше
        if (id == null || id.equals(""))
        {
            id = getIDFromCache(GetIDType.ANOTHERAPPCACHE, cnt, callSource, callDestination); //если в локальном кэше пусто, тогда смотрим в кэше приложения callDestination
            if (id == null || id.equals("")) {
                try {
                    id = getOriginalID(cnt);
                }catch (Exception e){
                }
            }
            if (id == null || id.equals("")) {
                id = generateGUID();
            }
            saveToCache(cnt, id);
        }
        return id;
    }
}
