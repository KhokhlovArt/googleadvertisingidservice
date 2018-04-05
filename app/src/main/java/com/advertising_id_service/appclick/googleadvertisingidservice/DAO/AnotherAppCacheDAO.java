package com.advertising_id_service.appclick.googleadvertisingidservice.DAO;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.advertising_id_service.appclick.googleadvertisingidservice.GUID.GUID;
import com.advertising_id_service.appclick.googleadvertisingidservice.LibContentProvider;

public class AnotherAppCacheDAO extends BaseDAO {
    String callSource;
    String callDestination;

    public AnotherAppCacheDAO setCallDestination(String callDestination) {
        this.callDestination = callDestination;
        return this;
    }

    public AnotherAppCacheDAO setCallSource(String callSource) {
        this.callSource = callSource;
        return this;
    }

    @Override
    public boolean save(GUID id) {
        return false;
    }

    @Override
    public GUID get() {
        String result = null;
        if (!callDestination.toString().equals(context.getPackageName().toString())
                && !callDestination.equals("") && callDestination != null
                && !callSource.equals("") && callSource != null
                && !callSource.equals(callDestination)) {
            Uri CONTACT_URI = Uri.parse(LibContentProvider.QUERY_URI + "/" + callDestination);
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(CONTACT_URI, null, null, null, null);
                int firstNameColIndex = cursor.getColumnIndex(LibContentProvider.GAID_COL_NAME);
                while (cursor.moveToNext()) {
                    result = cursor.getString(firstNameColIndex);
                }
            }
            catch (Exception e){
            }
            finally {
                if(cursor != null) {
                    cursor.close();
                }
            }
        }
        GUID guid = new GUID(result);
        return guid;
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
