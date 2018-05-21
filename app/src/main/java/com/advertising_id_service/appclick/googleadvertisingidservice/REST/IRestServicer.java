package com.advertising_id_service.appclick.googleadvertisingidservice.REST;


import android.content.Context;
import android.support.v4.app.LoaderManager;

import com.advertising_id_service.appclick.googleadvertisingidservice.InstallationInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;

/* Интерфейс IRestServicer служит для получения доступа к REST-методам:
*  Публичные:
*    create          - создает новый GUID в базе
*    install         - обновляет информацию о инсталяции
*    read            - в зависимости от переданного параметра делает запрос к базе по соответствующему идентификатору
*    delete          - удаляет GUID
*    update          - обновляет GUID
* */
public interface IRestServicer {
    ResultCreate  create(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass);
    ResultInstall install(final Context cnt, LoaderManager lm, final String callDestination, final InstallationInfo installInfo, final String login, final String pass);
    ResultRead    read(final Context cnt, LoaderManager lm, final IApi.RestReadType readType, final String callDestination, final String login, final String pass);
    ResultDelete  delete(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass);
    ResultUpdate  update(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass);
    void          sendLog(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass, String downloadId, String comment);
}
