package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

public class LibContentProvider extends ContentProvider {
    static public String GAID_COL_NAME = "gaid";
    static public String QUERY_URI = "content://advertising_id_service.appclick.googleadvertisingidservice/get_gaid";
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        //String data = new GoogleAdvertisingIdGetter().getIDFromCache(getContext(), "", "");
        String data = null;
        try {
            data = new GoogleAdvertisingIdGetter().getGAID(getContext(), "");
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        String[] matrixColumns = {GAID_COL_NAME};
        MatrixCursor gaid_cursor = new MatrixCursor(matrixColumns);
        Object[] mRow = new Object[1];
        mRow[0] = (uri.toString().equals(QUERY_URI + "/" + getContext().getPackageName().toString())) ? data : null;
        gaid_cursor.addRow(mRow);
        return gaid_cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
