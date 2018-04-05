package com.advertising_id_service.appclick.googleadvertisingidservice.DAO;

import android.content.Context;

public abstract class BaseDAO implements IDAO {
    Context context;

    public IDAO setContext(Context context) {
        this.context = context;
        return this;
    }
}
