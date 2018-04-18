package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.FilesSearcher;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


//Класс содержит информацию об инсталяции
public class InstallationInfo {
    public static final String INSTALLATION_GUID       = "installation_info_guid";
    public static final String INSTALLATION_DATE_START = "installation_info_date_start";
    private String PREFERENCES_SESSION                 = "install_info_pref_sesion";

    public String guid;                  //guid устройства
    public String installation_guid;     //идентификатор инсталляции приложения, генерируется как GUID с значением  “random_r” параметра  guid_type.
    public String app_id;                //идентификатор приложения package_name), непосредственно обратившегося в storage (в таблице install)
    public String apk_guid;              //идентификатор PublisherId, может быть массивом (дистрибутива на устройстве, из которого была произведена инсталляция приложения).
    public String apk_distr_path;        //путь на устройстве, не включая имя и расширение файла (отдельный справочник вместе с apk_guid)
    public String apk_istallation_path;  //путь, куда было установлено приложение
    public String date;                  //дата-время первого запуска(время первого запроса guid-а)
    private PublisherIDMask mask;        //Маска по которой осуществлялся поиск PublisherID

    public InstallationInfo(){}

    public InstallationInfo(Context cnt, String callDestination, PublisherIDMask mask) {
        setMask(mask);
        updateGuid(cnt, callDestination);
        updateInstallationGuid(cnt);
        updateAppId(cnt);
        updatApkGuid(cnt, mask);
        updatApkDistrPath(cnt, mask);
        updatApkIstallationPath(cnt);
        updatDate(cnt);
    }

    public void updateGuid(Context cnt, String callDestination) {

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
        guid = result;
    }

    public void updateInstallationGuid(Context cnt) {
        String id = cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).getString(INSTALLATION_GUID, null);
        if (id==null) {
            id = new GoogleAdvertisingIdGetter().generateGUID(IGoogleAdvertisingIdGetter.GenerateIDType.REAL_GUID, cnt);
            cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).edit().putString(INSTALLATION_GUID, id).apply();
        }
        installation_guid = id;
    }

    public void updateAppId(Context cnt) {
        app_id = cnt.getPackageName();
    }

    public void updatApkGuid(Context cnt, PublisherIDMask mask) {
        if (mask!=null) {
            List<String> data = new GoogleAdvertisingIdGetter().getFilePublisherIDs(cnt, mask);
            apk_guid = "" + data;
        }
    }

    public void updatApkDistrPath(Context cnt, PublisherIDMask mask) {
        if (mask!=null) {
            List files;
            FilesSearcher fs = new FilesSearcher();
            files = fs.getPublisherFiles(cnt, mask.getPrefix().split(","), mask.getSeporator(), mask.getExtension().split(","));
            apk_distr_path = "" + files;
        }
    }

    public void updatApkIstallationPath(Context cnt) {
        apk_istallation_path = cnt.getApplicationInfo().dataDir;
    }

    public void updatDate(Context cnt) {
        String start_date = cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).getString(INSTALLATION_DATE_START, null);
        if (start_date==null) {
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            start_date = formatter.format(currentTime);
            cnt.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).edit().putString(INSTALLATION_DATE_START, start_date).apply();
        }
        date = start_date;
    }

    public static void saveDateFirstStart(Context cnt) {
        String session = "saveDateFirstStart_session";
        String start_date = cnt.getSharedPreferences(session, Context.MODE_PRIVATE).getString(INSTALLATION_DATE_START, null);
        if (start_date==null) {
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            start_date = formatter.format(currentTime);
            cnt.getSharedPreferences(session, Context.MODE_PRIVATE).edit().putString(INSTALLATION_DATE_START, start_date).apply();
        }
    }

    public PublisherIDMask getMask() {
        return mask;
    }

    public void setMask(PublisherIDMask mask) {
        this.mask = mask;
    }
}
