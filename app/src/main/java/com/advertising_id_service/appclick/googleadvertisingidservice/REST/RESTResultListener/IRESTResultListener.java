package com.advertising_id_service.appclick.googleadvertisingidservice.REST.RESTResultListener;

//CallBack-класс для вывода результата REST запроса.
// метод onResult вызывается при окончании работа REST запроса при асинхронном запуске
// int resCode - код результата (0 - результат равен NULL-у, 1- все хорошо, 2- не опознанная ошибка... )
// T res       - результат определенного типа пришедший от сервера
public interface IRESTResultListener <T> {
    void onResult(int resCode, T res);
}
