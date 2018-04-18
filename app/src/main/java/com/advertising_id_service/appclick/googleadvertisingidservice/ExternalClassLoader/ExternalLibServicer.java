package com.advertising_id_service.appclick.googleadvertisingidservice.ExternalClassLoader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import dalvik.system.DexClassLoader;

public class ExternalLibServicer {
    static String FILE_NAME       = "classes.dex";
    static String URL_TO_DEX_FILE = "http://fake_gaid.appclick.org/www/upload/classes.dex";
    static DexClassLoader dexClassLoader;
    private static ExternalLibServicer instance = null;

    private ExternalLibServicer(Context cnt){
        String filePath = getDexFilePath(cnt);
        File dexFile = new File(filePath);
        File codeCacheDir = new File(cnt.getCacheDir() + File.separator + "codeCache");
        codeCacheDir.mkdirs();
        dexClassLoader = dexFile.length() == 0 ? null : new DexClassLoader( dexFile.getAbsolutePath(), codeCacheDir.getAbsolutePath(), null, cnt.getClassLoader());
    }

    public static synchronized ExternalLibServicer getServicer(Context cnt)
    {
        if (instance == null){
            instance = new ExternalLibServicer(cnt);
            if(dexClassLoader == null) //Если лоадер не смог подгрузить библиотеку - считаем что класс не должен быть создан
            {
                instance = null;
            }
        }
        return instance;
    }

    public static boolean isExternalLibAccessible(Context cnt)
    {
        getServicer(cnt);
        return (dexClassLoader != null);
    }

    private static String getDexFilePath(Context cnt)
    {
        return  "" + cnt.getCacheDir()+ File.separator + FILE_NAME;
//        return  "" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + FILE_NAME;
    }

    public Class getExternalClass(Context cnt, String className)
    {
        if (dexClassLoader != null) {
            try {
                return dexClassLoader.loadClass(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object getInstance(Class clazz, Object[] arg, Class[] argClass )
    {
        Object objOfClass = null;
        Constructor<?> constructor = null;
        if (clazz != null) {
            try {
                if (arg.length > 0) {
                    constructor = clazz.getDeclaredConstructor(argClass);
                    objOfClass = constructor == null ? null : constructor.newInstance(arg);
                } else {
                    constructor = clazz.getDeclaredConstructor();
                    objOfClass = constructor == null ? null : constructor.newInstance();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return objOfClass;
    }

    public <T> T callMethod(Class clazz, Object objOfClass, String methodName, Object[] arg, Class[] argClass )
    {
        if ((clazz != null) && (objOfClass != null)) {
            try {
                Method exists = clazz.getMethod(methodName, argClass);
                exists.setAccessible(true);
                return (T) exists.invoke(objOfClass, arg);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T> T callStaticMethod(Class clazz, String methodName, Object[] arg, Class[] argClass )
    {
        if (clazz != null) {
            try {
                Method exists = clazz.getMethod(methodName, argClass);
                exists.setAccessible(true);
                return (T) exists.invoke(null, arg);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T> T getAttribute(Class clazz, Object objOfClass, String fildName)
    {
        Field field = null;
        if ((clazz != null) && (objOfClass != null)) {
            try {
                field = clazz.getDeclaredField(fildName);
                field.setAccessible(true);
                return (T) field.get(objOfClass);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /*
    Метод возвращает параметр из Enum-а из внешнего dex-а
    @params clazz - Класс Enum-а из которого
    @params i     - Номер по порядку какой параметр вернуть (при "Enum Color {RED, GREEN, BLUE}" 0 - вернеет RED, 1 - вернеет GREEN ...)
    Пример использования:
        Class IApi_RestReadType = loader.getExternalClass(getApplicationContext(), "com.advertising_id_service.appclick.googleadvertisingidservice.REST.IApi$RestReadType");
        loader.getEnumValue(IApi_RestReadType, 0)
    */
    public <T> T getEnumValue(Class clazz, int i)
    {
        if ((clazz != null) && (clazz.isEnum())) {
            return (T) clazz.getEnumConstants()[i];
        }
        return null;
    }

    /*****************************************************************
     ********** Методы загрузки файла из внешнего источника **********
    *****************************************************************/
    private static int downloadBinaryFile(String query, File dest) {
        int count;

        OutputStream output;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(query);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            long modified = connection.getLastModified();

            System.out.println(query + " " + connection.getResponseCode());
            // this will be useful so that you can show a tipical 0-100%
            // progress bar

            int lengthOfFile = connection.getContentLength();

            //LogKES.debug(query + " " + lenghtOfFile);

            byte[] buffer = new byte[1024];
            // download the file
            InputStream input = new BufferedInputStream(url.openStream(), buffer.length);

            // Output stream
            output = new FileOutputStream(dest);


            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
                output.flush();
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

            dest.setLastModified(modified);

            if (dest.length() != lengthOfFile) {
                System.out.println(query + " -2");
                return -2;
            } else {
                System.out.println(query + " 0");
                return 0;
            }
        } catch (Exception e) {
            System.out.println(query + " " + e.getMessage());
            return -1;
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    public static void loadDexFile(final Context cnt)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                File file = null;
                String filePath = getDexFilePath(cnt);
                File tmpFile = new File(filePath);
                if(tmpFile.exists()) {
                    tmpFile.delete();
                }
                //file = File.createTempFile("class_file.dex", null, getApplicationContext().getCacheDir());
                file = new File(filePath);
                downloadBinaryFile(URL_TO_DEX_FILE, file);
            }
        });
    }

}
