package com.advertising_id_service.appclick.googleadvertisingidservice.IdGenerators;

import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.GUID.GUID;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RandomIDGenerator extends IDGenerator implements IGenerator {
    @Override
    public GUID generateId() {
//        Date currentTime = Calendar.getInstance().getTime();
//        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHH-mmss-SSS");
//        GUID id = new GUID(formatter.format(currentTime) + getRandomString(1) + "-" + getRandomString(4) + "-" + getRandomString(12));

        GUID id = new GUID("fakega-id" + getRandomString(2) + "-" + getRandomString(4) + "-" + getRandomString(4) + "-" + getRandomString(12));
        return id;
    }
}
