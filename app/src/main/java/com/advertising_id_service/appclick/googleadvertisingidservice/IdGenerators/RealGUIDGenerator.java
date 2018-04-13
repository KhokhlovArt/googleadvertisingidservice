package com.advertising_id_service.appclick.googleadvertisingidservice.IdGenerators;

import com.advertising_id_service.appclick.googleadvertisingidservice.GUID.GUID;

public class RealGUIDGenerator extends IDGenerator implements IGenerator {


    @Override
    public GUID generateId() {
        GUID id = new GUID(getRandomHEXString(8) + "-" + getRandomHEXString(4)  + "-" + getRandomHEXString(4) + "-" + getRandomHEXString(4) + "-" + getRandomHEXString(12));
        return id;
    }
}
