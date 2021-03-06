package com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import com.advertising_id_service.appclick.googleadvertisingidservice.InstallationInfo;
import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.IApi;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.BaseInputParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.CreateParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.DeleteParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.InstallParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.ReadParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.InputParameters.UpdateParameters;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultCreate;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultDelete;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultInstall;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultRead;
import com.advertising_id_service.appclick.googleadvertisingidservice.REST.Results.ResultUpdate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.List;

public interface IGoogleAdvertisingIdGetter {
    public enum SaveIDType {
        DEFAULT,
        SELF_CASHE
    }
    public enum GenerateIDType {
        DEFAULT,
        MIX,
        GUID_TOOL,
        REAL_GUID,
        RANDOM
    }
    public enum GetIDType {
        DEFAULT,
        ANOTHERAPPCACHE
    }

    public enum PublusherIDType {
        DEFAULT,
        INNER,
        FROM_FILE
    }

    String getVersion(Context cnt);
    // Метод получения оригинального GAID-а
    // @param cnt - контекст в рамках которого возвращается GAID
    // @return строку содержащую оригинальный GAID (Пример: 2QEsaZ-1ds2-547F-FxSa-SADdAwDALoix )
    String getOriginalID(final Context cnt)  throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException;

    // Метод генерации фейкового идентификатора
    // @param control_parameter - управляющий параметр для выбора способа генерации ID
    // @param cnt - контекст в рамках которого возвращается GAID
    // @return строку содержащую фейковый GUID в зависимости от управляющего параметра(Пример: 250988-1552-547F-FxSa-SADdAwDALoix )
    String generateGUID(GenerateIDType control_parameter, Context cnt);

    // Метод сохраняющий ID в кеш
    // @param control_parameter - параметр определяющий каим образом будет идти сохранение
    // @param id - id в текстовом виде который будет сохранён
    // @param cnt - контекст приложения
    // @return true - если сохранение прошло без ошибок, false - если id не был сохранён
    //boolean saveToCache(SaveIDType control_parameter, Context cnt, String id );

    // Метод получающий ID из кеша
    // @param control_parameter - параметр определяющий откуда будет идти загрузка
    // @param cnt - контекст приложения
    // @param callSource - имя пакета приложения из которого идёт вызов
    // @param callDestination - имя пакета приложения в кэше которого надо смотреть GAID изначально
    // @return true - сохраненный ID
    //String getIDFromCache(GetIDType control_parameter, Context cnt, String callSource, String callDestination );

    // Метод который загружает закэшированный ID. Если кэш пуст, то возвращает оригинальгный ID
    // устройства а в случае его отсутствия генерирует фейковый и сохраняет его в кэш
    // @param cnt - контекст приложения
    // @param callSource - имя пакета приложения из которого идёт вызов
    // @param callDestination - имя пакета приложения в кэше которого надо смотреть GAID изначально
    // @return  - фейковый или реальный ID
    String getFakeGaid(final Context cnt /*, String callSource, String callDestination*/) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException;

    //Метод который ищет все файлы по определеннйо маске и извлекает PublisherID из этих имен файлов
    //@param control_parameter - управляющий параметр
    //@param cnt - контекст приложения
    //@param mask - маска по которой идё тпоиск
    // @return  -  List всех идентификаторов из файлов найденых на устройстве
    List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask);

    //Метод который ищет возвращает пробитый в метаданных PublisherID
    //@param control_parameter - управляющий параметр
    //@param cnt - контекст приложения
    //@param key - ключ мотоданных по которому надо искать
    // @return  -  List всех идентификаторов из файлов найденых на устройстве
    String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key);

    //Метод сохраняющий в кэш текущего GAID-а произвольный GAID.
    //@param cnt - контекст приложения
    //@param id  - ID который будет сохранен
    void setGAID(final Context cnt, String id);

    String getGAID(final Context cnt, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException;

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************
    ResultCreate  rest_create(CreateParameters param);
    ResultUpdate  rest_update(UpdateParameters param);
    ResultInstall rest_install(InstallParameters param);
    ResultRead    rest_read(ReadParameters param);
    ResultDelete  rest_delete(DeleteParameters param);

    //*****************************************************************************
    //*************** Методы работы с обновлением библиотеки **********************
    //*****************************************************************************
    boolean libUpdate(Context cnt, LoaderManager lm, String GAID);
    //<T> T callExternalMethod(String methodName, HashMap params);

    void initialize(Context cnt, LoaderManager lm);
}
