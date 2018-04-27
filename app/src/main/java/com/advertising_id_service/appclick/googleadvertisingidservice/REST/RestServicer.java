package com.advertising_id_service.appclick.googleadvertisingidservice.REST;

public class RestServicer {
    public static final int LOADER_CREATE  = 1;
    public static final int LOADER_INSTALL = 2;
    public static final int LOADER_READ    = 3;
    public static final int LOADER_DELETE  = 4;
    public static final int LOADER_UPDATE  = 5;

    public static final String TAG           = "GAID_REST";
    public static final String BASE_URL      = "https://fake_gaid.appclick.org/gaid";

    public  static final String ACTION_CREATE  = "create";
    public  static final String ACTION_INSTALL = "install";
    public  static final String ACTION_READ    = "read";
    public  static final String ACTION_DELETE  = "delete";
    public  static final String ACTION_UPDATE  = "update";


    public enum TypeRestServicer{
        SIMPLE,
        RETROFIT
    }

    public static synchronized IRestServicer getRestServicer(TypeRestServicer type)
    {
        IRestServicer res = null;
        switch (type) {
            case SIMPLE:
                res =  RestServicerSimple.getRestServicer();
                break;
            case RETROFIT:
                res =  RestServicerRetrofit.getRestServicer();
                break;
            default:
                res =  RestServicerSimple.getRestServicer();
                break;
        }
        return res;
    }

}
