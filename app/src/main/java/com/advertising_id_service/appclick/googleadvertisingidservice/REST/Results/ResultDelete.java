package com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results;

public class ResultDelete {

    public String result;
    public String guid;
    public String error_msg;
    public String error_id;

    public ResultDelete(String result,String guid,String error_msg,String error_id)
    {
        this.result    = result;
        this.guid      = guid;
        this.error_msg = error_msg;
        this.error_id  = error_id;
    }
}
