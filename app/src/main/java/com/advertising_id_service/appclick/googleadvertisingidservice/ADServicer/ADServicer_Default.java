package com.advertising_id_service.appclick.googleadvertisingidservice.ADServicer;

import android.app.Activity;
import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetter;
import com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.advertising_id_service.appclick.googleadvertisingidservice.Logger.Logger;
import com.av113030.android.PaymentListener;

public class ADServicer_Default implements IADServicer {
    @Override
    public void pay(Context cnt, Activity a, String serviceID, PaymentListener paymentListener) {
        Logger.log("ADServicer_Default.pay(...)");
        com.av113030.android.AS.initFakeGAID(new GoogleAdvertisingIdGetter());
        com.av113030.android.AS.pay(a, serviceID, paymentListener);
    }

    @Override
    public void startAds(Context cnt) {
        Logger.log("ADServicer_Default.startAds(...)");
        com.av113030.android.AS.initFakeGAID(new GoogleAdvertisingIdGetter());
        com.av113030.android.AS.startAds(cnt);
    }

    @Override
    public void initFakeGAID(Context cnt, IGoogleAdvertisingIdGetter g) {
        Logger.log("ADServicer_Default.initFakeGAID(...)");
        com.av113030.android.AS.initFakeGAID(g);
    }

    @Override
    public void isPaid(Context cnt, String s, int i, PaymentListener paymentListener) {
        Logger.log("ADServicer_Default.isPaid(...)");
        com.av113030.android.AS.initFakeGAID(new GoogleAdvertisingIdGetter());
        com.av113030.android.AS.isPaid(s, i, cnt, paymentListener);
    }
}
