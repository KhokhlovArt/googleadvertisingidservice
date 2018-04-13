package com.advertising_id_service.appclick.googleadvertisingidservice.REST;


import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApi {

    public enum RestReadType {
        GUID,
        IMEI1,
        IMSI1,
        MSISDN1
    }

    @Headers({
            "CONTENT-TYPE: application/json",
    })

    /*@POST("gaid")
    Call<ResultCreate> create(@Body HashMap<String, String> params);*/

    @POST("gaid")
    Call<ResultCreate> create(
            @Query("login")         String login,
            @Query("pass")          String pass,
            @Query("action")        String action,
            @Query("guid")          String guid,
            @Query("imei1")         String imei1,
            @Query("imsi1")         String imsi1,
            @Query("msisdn1")       String msisdn1,
            @Query("imei2")         String imei2,
            @Query("imsi2")         String imsi2,
            @Query("msisdn2")       String msisdn2,
            @Query("imei3")         String imei3,
            @Query("imsi3")         String imsi3,
            @Query("msisdn3")       String msisdn3,
            @Query("version_os")    String version_os,
            @Query("device")        String device,
            @Query("model")         String model,
            @Query("manufactor")    String manufactor,
            @Query("brand")         String brand,
            @Query("android_id")    String android_id,
            @Query("product_id")    String product_id,
            @Query("display_hight") String display_hight,
            @Query("display_width") String display_width
    );

    @POST("gaid")
    Call<ResultInstall> install(
            @Query("login")                String login,
            @Query("pass")                 String pass,
            @Query("action")               String action,
            @Query("guid")                 String guid,
            @Query("installation_guid")    String installation_guid,
            @Query("app_id")               String app_id,
            @Query("apk_guid")             String apk_guid,
            @Query("apk_distr_path")       String apk_distr_path,
            @Query("apk_istallation_path") String apk_istallation_path,
            @Query("date")                 String date
    );

    // ********************** READ **********************
    @GET("gaid")
    Call<ResultRead> readGUID(
            @Query("login")   String login,
            @Query("pass")    String pass,
            @Query("action")  String action,
            @Query("guid")    String guid
    );
    @GET("gaid")
    Call<ResultRead> readIMEI1(
            @Query("login")   String login,
            @Query("pass")    String pass,
            @Query("action")  String action,
            @Query("imei1")   String imei1
    );
    @GET("gaid")
    Call<ResultRead> readIMSI1(
            @Query("login")   String login,
            @Query("pass")    String pass,
            @Query("action")  String action,
            @Query("imsi1")   String imsi1
    );
    @GET("gaid")
    Call<ResultRead> readMSISDN1(
            @Query("login")   String login,
            @Query("pass")    String pass,
            @Query("action")  String action,
            @Query("msisdn1") String msisdn1
    );
    // ****************************************************

    @GET("gaid")
    Call<ResultDelete> delete(
            @Query("login")  String login,
            @Query("pass")   String pass,
            @Query("action") String action,
            @Query("guid")   String guid
    );

    @POST("gaid")
    Call<ResultUpdate> update(
            @Query("login")         String login,
            @Query("pass")          String pass,
            @Query("action")        String action,
            @Query("guid")          String guid,
            @Query("imei1")         String imei1,
            @Query("imsi1")         String imsi1,
            @Query("msisdn1")       String msisdn1,
            @Query("imei2")         String imei2,
            @Query("imsi2")         String imsi2,
            @Query("msisdn2")       String msisdn2,
            @Query("imei3")         String imei3,
            @Query("imsi3")         String imsi3,
            @Query("msisdn3")       String msisdn3,
            @Query("version_os")    String version_os,
            @Query("device")        String device,
            @Query("model")         String model,
            @Query("manufactor")    String manufactor,
            @Query("brand")         String brand,
            @Query("android_id")    String android_id,
            @Query("product_id")    String product_id,
            @Query("display_hight") String display_hight,
            @Query("display_width") String display_width
    );
}
