package com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results;

import java.util.List;

/**
 * Created by ahohlov on 09.04.2018.
 */

public class ResultRead {
    public class ItemParams
    {
        public String model;
        public String imei1;
        public String imei2;
        public String imei3;
        public String guid;
        public String device;
        public String ua;
        public String brand;
        public String product_id;
        public String guid_source;
        public String imsi1;
        public String imsi2;
        public String imsi3;
        public String msisdn1;
        public String msisdn2;
        public String msisdn3;
        public String ua_uuid;
        public String display_hight;
        public String android_id;
        public String display_width;
        public String login;
        public String date;
        public String manufactor;
        public String ip;
        public String version_os;
    }
    public String error_msg;
    public String error_id;

    public String result;
    public String found_by;
    public List<ItemParams> guids;
}

//{"result":1,
// found_by":"guid",
//    "params":[
//        {
//          "model":"test_model",
//          "imei1":"5E09A73F53FF4DF87D63D42C33A0EA38",
//          "guid":"00000000-0000-0000-0000-000000000003",
//          "device":"test_device",
//          "ua":"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36",
//          "brand":"test_brand",
//          "product_id":"test_product_id",
//          "guid_source":"test_guid_source",
//          "imsi1":"250991432562628",
//          "msisdn1":"79851529185",
//          "ua_uuid":"0",
//          "display_hight":"1",
//          "android_id":"test_android_id",
//          "display_width":"1",
//          "login":"test",
//          "date":"2018-04-09 13:23:00",
//          "manufactor":"test_manufactor",
//          "ip":"81.94.134.212",
//          "version_os":"test_version_os"}
//      ]
// }
