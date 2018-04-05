package com.advertising_id_service.appclick.googleadvertisingidservice.GUID;

public class GUID implements IGUID{
    private String id;

    public GUID(String _id){
        id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
