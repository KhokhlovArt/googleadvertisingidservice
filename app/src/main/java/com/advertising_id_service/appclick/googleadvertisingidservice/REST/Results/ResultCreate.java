package com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results;

//{"result":1,"guid":"8aca2500-3994-11e8-beb6-94de80726fff"}
public class ResultCreate {

    public String result;
    public String guid;

    public String error_msg;
    public String error_id;
    public String dynamic_data;

    public ResultCreate(String result, String guid, String error_msg, String error_id)
    {
        this.result    = result;
        this.guid      = guid;
        this.error_msg = error_msg;
        this.error_id  = error_id;
    }
}
