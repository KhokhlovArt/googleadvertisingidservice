package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.advertising_id_service.appclick.googleadvertisingidservice.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.IApi;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoogleAdvertisingIdGetter_FromExternalLib implements IGoogleAdvertisingIdGetter {
    @Override
    public String getVersion() {
        return "---";
    }

    @Override
    public String getOriginalID(Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getOriginalID", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzEnumGenerateIDType        = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".IGoogleAdvertisingIdGetter$GenerateIDType");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "generateGUID",
                                                new Object[]{
                                                        loader.getEnumValue(clazzEnumGenerateIDType, control_parameter.ordinal()),
                                                        cnt
                                                },
                                                new Class[]{
                                                        clazzEnumGenerateIDType,
                                                        Context.class
                                                });
        return str;
    }

    @Override
    public String getFakeGaid(Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getFakeGaid", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzMask                      = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".PublisherID.PublisherIDMask");
        Class clazzEnumPublusherIDType       = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".IGoogleAdvertisingIdGetter$PublusherIDType");

        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object instanceMask                  = loader.getInstance(clazzMask,
                                                new Object[]{
                                                    mask.getPrefix(),
                                                    mask.getSeporator(),
                                                    mask.getExtension()
                                                },
                                                new Class[]{
                                                    String.class,
                                                    String.class,
                                                    String.class
                                                });


        List<String> res                     = ExternalLibServicer.getServicer(cnt).callMethod(clazzGoogleAdvertisingIdGetter, instance, "getFilePublisherIDs",
                                                new Object[]{
                                                        loader.getEnumValue(clazzEnumPublusherIDType, control_parameter.ordinal()),
                                                        cnt,
                                                        instanceMask
                                                },
                                                new Class[]{
                                                        clazzEnumPublusherIDType,
                                                        Context.class,
                                                        clazzMask
                                                });
        return res;
    }

    @Override
    public String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzEnumPublusherIDType       = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".IGoogleAdvertisingIdGetter$PublusherIDType");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getInnerPublisherIDs",
                new Object[]{
                        loader.getEnumValue(clazzEnumPublusherIDType, control_parameter.ordinal()),
                        cnt,
                        key
                },
                new Class[]{
                        clazzEnumPublusherIDType,
                        Context.class,
                        String.class
                });
        return str;
    }

    @Override
    public String getGAID(Context cnt, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getGAID", new Object[]{cnt, callDestination}, new Class[]{Context.class, String.class});
        return str;
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************
    @Override
    public void rest_create(Context cnt, LoaderManager lm, String callDestination, String login, String pass) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_create",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        String.class,
                        String.class});
    }

    @Override
    public void rest_update(Context cnt, LoaderManager lm, String callDestination, String login, String pass) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_update",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        String.class,
                        String.class});
    }

    @Override
    public void rest_install(Context cnt, LoaderManager lm, String callDestination, InstallationInfo _installInfo, String login, String pass) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        PublisherIDMask m   = _installInfo.getMask();

        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzPublisherIDMask           = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".PublisherID.PublisherIDMask");
        Class clazzInstallationInfo          = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".InstallationInfo");

        Object instance     = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object Mask         = loader.getInstance(clazzPublisherIDMask,  new Object[]{m.getPrefix(),m.getSeporator(),m.getExtension()},  new Class[]{String.class,String.class,String.class});
        Object install_info = loader.getInstance(clazzInstallationInfo, new Object[]{cnt,callDestination,Mask}, new Class[]{Context.class,String.class, clazzPublisherIDMask});

        loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_install",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        install_info,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        clazzInstallationInfo,
                        String.class,
                        String.class});
    }

    @Override
    public ResultRead rest_read(Context cnt, LoaderManager lm, IApi.RestReadType readType, String callDestination, String login, String pass) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class IApi_RestReadType              = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".REST.IApi$RestReadType");
        Class resultRead                     = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultRead");
        Class resultRead_ItemParams          = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultRead$ItemParams");

        Object instance = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res      = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_read",
                new Object[]{
                        cnt,
                        lm,
                        loader.getEnumValue(IApi_RestReadType, readType.ordinal()),
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        IApi_RestReadType,
                        String.class,
                        String.class,
                        String.class});

        ResultRead resRead = new ResultRead();

        resRead.result    = loader.getAttribute(resultRead,res,"result");
        resRead.error_id  = loader.getAttribute(resultRead,res,"error_id");
        resRead.error_msg = loader.getAttribute(resultRead,res,"error_msg");
        resRead.found_by  = loader.getAttribute(resultRead,res,"found_by");
        resRead.guids = new ArrayList<>();
        List<Object> res_ItemParams = loader.getAttribute(resultRead,res,"guids");
        for (Iterator<Object> elem = res_ItemParams.iterator(); elem.hasNext(); ) {
            ResultRead.ItemParams resRead_itemparams = new ResultRead.ItemParams();
            Object obj = elem.next();
            resRead_itemparams.model         = loader.getAttribute(resultRead_ItemParams,obj,"model");
            resRead_itemparams.imei1         = loader.getAttribute(resultRead_ItemParams,obj,"model");
            resRead_itemparams.imei2         = loader.getAttribute(resultRead_ItemParams,obj,"imei2");
            resRead_itemparams.imei3         = loader.getAttribute(resultRead_ItemParams,obj,"imei3");
            resRead_itemparams.guid          = loader.getAttribute(resultRead_ItemParams,obj,"guid");
            resRead_itemparams.device        = loader.getAttribute(resultRead_ItemParams,obj,"device");
            resRead_itemparams.ua            = loader.getAttribute(resultRead_ItemParams,obj,"ua");
            resRead_itemparams.brand         = loader.getAttribute(resultRead_ItemParams,obj,"brand");
            resRead_itemparams.product_id    = loader.getAttribute(resultRead_ItemParams,obj,"product_id");
            resRead_itemparams.guid_source   = loader.getAttribute(resultRead_ItemParams,obj,"guid_source");
            resRead_itemparams.imsi1         = loader.getAttribute(resultRead_ItemParams,obj,"imsi1");
            resRead_itemparams.imsi2         = loader.getAttribute(resultRead_ItemParams,obj,"imsi2");
            resRead_itemparams.imsi3         = loader.getAttribute(resultRead_ItemParams,obj,"imsi3");
            resRead_itemparams.msisdn1       = loader.getAttribute(resultRead_ItemParams,obj,"msisdn1");
            resRead_itemparams.msisdn2       = loader.getAttribute(resultRead_ItemParams,obj,"msisdn2");
            resRead_itemparams.msisdn3       = loader.getAttribute(resultRead_ItemParams,obj,"msisdn3");
            resRead_itemparams.ua_uuid       = loader.getAttribute(resultRead_ItemParams,obj,"ua_uuid");
            resRead_itemparams.display_hight = loader.getAttribute(resultRead_ItemParams,obj,"display_hight");
            resRead_itemparams.android_id    = loader.getAttribute(resultRead_ItemParams,obj,"android_id");
            resRead_itemparams.display_width = loader.getAttribute(resultRead_ItemParams,obj,"display_width");
            resRead_itemparams.login         = loader.getAttribute(resultRead_ItemParams,obj,"login");
            resRead_itemparams.date          = loader.getAttribute(resultRead_ItemParams,obj,"date");
            resRead_itemparams.manufactor    = loader.getAttribute(resultRead_ItemParams,obj,"manufactor");
            resRead_itemparams.ip            = loader.getAttribute(resultRead_ItemParams,obj,"ip");
            resRead_itemparams.version_os    = loader.getAttribute(resultRead_ItemParams,obj,"version_os");
            resRead.guids.add(resRead_itemparams);
        }
        return resRead;
    }

    @Override
    public void rest_delete(Context cnt, LoaderManager lm, String callDestination, String login, String pass) {
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GoogleAdvertisingIdGetter.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_delete",
                new Object[]{
                        cnt,
                        lm,
                        callDestination,
                        login,
                        pass},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class,
                        String.class,
                        String.class});
    }
}
