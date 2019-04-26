package org.level.up.json;

public interface JsonService {

    String toJson (Object object);

    <T> T fromJson (String json, Class<T> classOfT);

    String toJsonField (Object object);

    <T> T fromJsonField (String json, Class <T> classOfT);

}
