package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;

import com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID.PublisherIDMask;
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

    String getGAID(final Context cnt, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException;
}
