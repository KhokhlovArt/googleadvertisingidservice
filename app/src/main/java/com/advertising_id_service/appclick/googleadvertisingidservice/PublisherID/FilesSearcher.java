package com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesSearcher {

    private Pattern p = null;

    private final int FILES = 0;
    private final int DIRECTORIES = 1;
    private final int ALL = 2;

    public List<String> getPublisherFiles(Context cnt, String[] prefix, String seporator, String[] extension) {
        List<String> allFiles = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkPermission(cnt)) {
                allFiles.add("No permissions for reading the storage");
                return allFiles;
            }
        }
        else
        {
            String permission = "android.permission.READ_EXTERNAL_STORAGE";
            int res = cnt.checkCallingOrSelfPermission(permission);
            if (res != PackageManager.PERMISSION_GRANTED)
            {
                allFiles.add("No permissions for reading the storage");
                return allFiles;
            }
        }

        if ((prefix.length == 0)|| (extension.length == 0)
                ||(prefix.equals("")) || (extension.equals("")))
        {
            return allFiles;
        }
        String pref = "(";
        for (int i = 0; i < prefix.length; i++ )
        {
            pref += prefix[i].toString();
            pref += i < prefix.length-1 ? "|" : "";
        }
        pref += ")";

        String ext = "(";
        for (int i = 0; i < extension.length; i++ )
        {
            ext += extension[i].toString();
            ext += i < extension.length-1 ? "|" : "";
        }
        ext += ")";

        String mask = pref + seporator + ".+" + ext;

        try {
            List<String> filePaths = getAllPaths(cnt);
            if (!filePaths.isEmpty())
            {
                for (Iterator<String> i = filePaths.iterator(); i.hasNext();) {
                    allFiles.addAll((ArrayList) find(i.next(), mask, 0));
                }
            }
            else
            {
                allFiles = (ArrayList) find(Environment.getExternalStorageDirectory().toString(), mask, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allFiles;
    }

    public String ejectApkGUID(String fullID, String pref, String seporator, String extension)
    {
        if (fullID.equals("") || pref.equals("") || seporator.equals("") || extension.equals(""))
        {
            return "";
        }
        String [] arr = fullID.toString().split("/");
        String strId = arr[arr.length-1];
        String[] tmp = pref.split(",");
        for (int i = 0; i < tmp.length; i++ )
        {
            if (strId.toUpperCase().substring(0,tmp[i].length()).equals(tmp[i].toUpperCase()))
            {
                strId = strId.replaceFirst("(?i)" + tmp[i], "");
                break;
            }
        }

        tmp = extension.split(",");
        for (int i = 0; i < tmp.length; i++ )
        {
            if (strId.length() >= tmp[i].length()) {
                strId = strId.substring(0, strId.length() - tmp[i].length());
                break;
            }
        }
        strId = strId.replaceFirst(seporator, "");

        return strId;
    }

    private boolean checkPermission(Context cnt) {
        int result = ContextCompat.checkSelfPermission(cnt, Manifest.permission.READ_EXTERNAL_STORAGE);
        return (result == PackageManager.PERMISSION_GRANTED);
    }

    private StorageManager getStorageManager(Context cnt) {
        return (StorageManager) cnt.getSystemService(Context.STORAGE_SERVICE);
    }
    /*
           Use reflection for detecting all storages as android do it
           probably doesn't work with USB-OTG
           works only on API 19+
           https://ru.smedialink.com/razrabotka/rabota-s-sd-kartoj-na-android-podvodnye-kamni/
     */
    private List<String> getAllPaths(Context cnt) {
        List<String> allPaths = new ArrayList<>();
        try {
            Class<?> storageVolumeClass = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = getStorageManager(cnt).getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClass.getMethod("getPath");
            Method getState = storageVolumeClass.getMethod("getState");
            Object getVolumeResult = getVolumeList.invoke(getStorageManager(cnt));
            final int length = Array.getLength(getVolumeResult);

            for (int i = 0; i < length; ++i) {
                Object storageVolumeElem = Array.get(getVolumeResult, i);
                String mountStatus = (String) getState.invoke(storageVolumeElem);
                if (mountStatus != null && mountStatus.equals("mounted")) {
                    String path = (String) getPath.invoke(storageVolumeElem);
                    if (path != null) {
                        allPaths.add(path);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allPaths;
    }

    private boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }

    private List find(String startPath, String mask, int objectType) throws Exception {
        if(startPath == null || mask == null) {
            throw new Exception("Error: no search parameters specified");
        }
        File topDirectory = new File(startPath);
        if(!topDirectory.exists()) {
            throw new Exception("Error: path does not exist");
        }

        if(!isExternalStorageReadable()) {
            throw new Exception("Error: External storage not available");
        }

        if(!mask.equals("")) {
            p = Pattern.compile(mask, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        }

        ArrayList res = new ArrayList();
        search(topDirectory, res, objectType);
        p = null;
        return res;
    }

    private void search(File topDirectory, List res, int objectType) {
        File[] list = topDirectory.listFiles();
        if( list == null){return;}
        for(int i = 0; i < list.length; i++) {
            if(list[i].isDirectory()) {
                if(objectType != FILES && accept(list[i].getName())) {
                    res.add(list[i]);
                }
                //выполняем поиск во вложенных директориях
                search(list[i], res, objectType);
            }
            else {
                if(objectType != DIRECTORIES && accept(list[i].getName())) {
                    res.add(list[i]);
                }
            }
        }
    }

    private boolean accept(String name) {
        Matcher m = p.matcher(name);
        return (p == null) ? true : m.matches();
    }
}
