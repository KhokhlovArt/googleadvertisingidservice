package com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import com.advertising_id_service.appclick.googleadvertisingidservice.REST.RESTResultListener.IRESTResultListener;

// Базовый класс параметров, в него вынесены общие части для всех входных параметров.
// @attr cnt              - контекст
// @attr lm               - LoaderManager для асинхронного вызова.
// @attr callDestination  - имя пакета из которого надо получать GAID (если из своего то передать null или "")
// @attr login            - логин клиента
// @attr pass             - пароль клиента
// @attr forceStart       - Параметр указывающий что если асинхронно не получилось запустить, то пробуем запустить синхронно.
// @attr asincStart       - асинхронно или нет вызывать метод. (Если вызывается асинхронно то автоматом запускается в отдельном потоке лоадера)
// @attr onResultListener - Реализация callback-интерфейса, вызывается при окончании асинхронного запроса
public class BaseInputParameters<T> {
    private Context cnt;
    private LoaderManager lm;
    private String callDestination;
    private String login;
    private String pass;
    private boolean forceStart;
    private boolean asincStart;
    private IRESTResultListener onResultListener;

    public BaseInputParameters(){
        forceStart = false;
    }

    public Context getCnt() {
        return cnt;
    }

    public T setCnt(Context cnt) {
        this.cnt = cnt;
        return (T) this;
    }

    public LoaderManager getLm() {
        return lm;
    }

    public T setLm(LoaderManager lm) {
        this.lm = lm;
        return (T) this;
    }

    public String getCallDestination() {
        return callDestination;
    }

    public T setCallDestination(String callDestination) {
        this.callDestination = callDestination;
        return (T) this;
    }

    public String getLogin() {
        return login;
    }

    public T setLogin(String login) {
        this.login = login;
        return (T) this;
    }

    public String getPass() {
        return pass;
    }

    public T setPass(String pass) {
        this.pass = pass;
        return (T) this;
    }

    public boolean isForceStart() {
        return forceStart;
    }

    public T setForceStart(boolean forceStart) {
        this.forceStart = forceStart;
        return (T) this;
    }

    public IRESTResultListener getOnResultListener() {
        return onResultListener;
    }

    public T setOnResultListener(IRESTResultListener onResultListener) {
        this.onResultListener = onResultListener;
        return (T) this;
    }

    public boolean isAsincStart() {
        return asincStart;
    }

    public T setAsincStart(boolean asincStart) {
        this.asincStart = asincStart;
        return (T) this;
    }
}
