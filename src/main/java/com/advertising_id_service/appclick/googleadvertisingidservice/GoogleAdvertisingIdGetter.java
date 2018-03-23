package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class GoogleAdvertisingIdGetter {

    public static String getId(final Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
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
        return realGAID.equals("") ? getFakeUUID(cnt) : realGAID;
    }

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

    private static String getFakeUUID (Context cntx) {
        String preferences_session = "session";
        String preferences = "FAKE_GAID";

        String fake_gaid = cntx.getSharedPreferences(preferences_session, MODE_PRIVATE).getString(preferences, "");
        if((fake_gaid == null) || (fake_gaid.equals(""))) {
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHH-mmss-SSS");
            String new_fake_gaid = formatter.format(currentTime) + getRandomString(1) + "-" + getRandomString(4) + "-" + getRandomString(12);
            cntx.getSharedPreferences(preferences_session, MODE_PRIVATE).edit().putString(preferences, new_fake_gaid).apply();
            return new_fake_gaid;
        }
        else
        {
            return fake_gaid;
        }
    }
}
