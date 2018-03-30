package com.advertising_id_service.appclick.googleadvertisingidservice;

import android.content.Context;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

public interface IGoogleAdvertisingIdGetter {
    public enum SaveIDType {
        DEFAULT
    }
    public enum GenerateIDType {
        DEFAULT,
        GUID_TOOL
    }
    public enum GetIDType {
        DEFAULT
    }


    // Метод получения оригинального GAID-а
    // @param cnt - контекст в рамках которого возвращается GAID
    // @return строку содержащую оригинальный GAID (Пример:  )
    String getOriginalID(final Context cnt)  throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException;

    // Метод генерации фейкового идентификатора
    // @param control_parameter - управляющий параметр для выбора способа генерации ID
    // @return строку содержащую оригинальный GAID (Пример: 250988-1552-547F-FxSa-SADdAwDALoix )
    String generateGUID(GenerateIDType control_parameter );

    // Метод сохраняющий ID в кеш
    // @param control_parameter - параметр определяющий каим образом будет идти сохранение
    // @param id - id в текстовом виде который будет сохранён
    // @param cnt - контекст приложения
    // @return true - если сохранение прошло без ошибок, false - если id не был сохранён
    boolean saveToCache(SaveIDType control_parameter, Context cnt, String id );

    // Метод получающий ID из кеша
    // @param control_parameter - параметр определяющий откуда будет идти загрузка
    // @param cnt - контекст приложения
    // @return true - сохраненный ID
    String getIDFromCache(GetIDType control_parameter, Context cnt, String callSource, String callDestination );

    // Метод который загружает закэшированный ID. Если кэш пуст, то возвращает оригинальгный ID
    // устройства а в случае его отсутствия генерирует фейковый и сохраняет его в кэш
    // @param cnt - контекст приложения
    // @return  - фейковый или реальный ID
    String getFakeGaid(final Context cnt, String callSource, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException;
}
