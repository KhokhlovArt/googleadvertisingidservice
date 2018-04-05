package com.advertising_id_service.appclick.googleadvertisingidservice.IdGenerators;

import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.GUID.GUID;

public interface IGenerator {
    GUID generateId();
    IGenerator setContext(Context cnt);
}
