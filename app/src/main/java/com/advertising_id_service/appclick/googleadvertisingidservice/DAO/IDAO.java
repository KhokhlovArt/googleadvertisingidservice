package com.advertising_id_service.appclick.googleadvertisingidservice.DAO;

import com.advertising_id_service.appclick.googleadvertisingidservice.GUID.GUID;


public interface IDAO {
    boolean save(GUID id);
    GUID get();
    boolean delete();
    boolean update(GUID id);
}
