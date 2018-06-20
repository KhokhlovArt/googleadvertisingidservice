package com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters;

import com.advertising_id_service.appclick.googleadvertisingidservice.REST.IApi;

// @param readType        - по какому из параемтров искать в базе (GAID, imei, imsi...)
public class ReadParameters extends BaseInputParameters<ReadParameters> {
    private IApi.RestReadType readType;

    public IApi.RestReadType getReadType() {
        return readType;
    }

    public ReadParameters setReadType(IApi.RestReadType readType) {
        this.readType = readType;
        return this;
    }
}
