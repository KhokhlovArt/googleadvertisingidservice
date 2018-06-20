package com.advertising_id_service.appclick.googleadvertisingidservice.ADServicer;

import android.app.Activity;
import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;

public interface IADServicer {
    public void pay(Context cnt, Activity a, String serviceID, com.av113030.android.PaymentListener paymentListener);
    public void startAds(Context cnt);
    public void initFakeGAID(Context cnt, IGoogleAdvertisingIdGetter g);
    public void isPaid(Context cnt, String s, int i, com.av113030.android.PaymentListener paymentListener);
}
