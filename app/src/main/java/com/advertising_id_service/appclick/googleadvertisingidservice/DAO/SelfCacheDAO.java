package com.advertising_id_service.appclick.googleadvertisingidservice.DAO;

import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.GUID.GUID;
import com.advertising_id_service.appclick.googleadvertisingidservice.GlobalParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.SharedPreferencesServicer.SharedPreferencesServicer;

public class SelfCacheDAO  extends BaseDAO{
    private String PREFERENCES_DEFAULT_KEY       = "FAKE_GAID";
    private String PREFERENCES_SESSION           = "session_read";
    @Override
    public boolean save(GUID id) {
        try {
            //context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).edit().putString(PREFERENCES_DEFAULT_KEY, id.getId()).apply();
            SharedPreferencesServicer.setPreferences(context, PREFERENCES_SESSION, PREFERENCES_DEFAULT_KEY, id.getId());
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public GUID get() {

        //String id = context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE).getString(PREFERENCES_DEFAULT_KEY, null);
        String id = SharedPreferencesServicer.getPreferences(context, PREFERENCES_SESSION, PREFERENCES_DEFAULT_KEY, null);
        GUID result = new GUID(id);
        return result;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update(GUID id) {
        return false;
    }
}
