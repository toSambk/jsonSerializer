package org.level.up.json;

public interface JsonDeserializer<T> {

    T deserialize(String json);

}
