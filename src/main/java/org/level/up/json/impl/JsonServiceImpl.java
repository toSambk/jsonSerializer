package org.level.up.json.impl;

import org.level.up.json.*;

import java.lang.reflect.Field;

import static java.lang.Integer.parseInt;

public class JsonServiceImpl
        implements JsonService {


    @Override
    public String toJson(Object object) {
        Class <?> clazz = object.getClass();

        SerializedBy annotation = clazz.getAnnotation(SerializedBy.class);

        if (annotation == null) {
            throw new RuntimeException("");
        }

        Class<? extends JsonSerializer> serializer = annotation.serializer();
        JsonSerializer jsonSerializer = JsonStorage.serializers.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(serializer.getSimpleName()))
                .findFirst()
                .get()
                .getValue();

        return jsonSerializer.serialize(object);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {

        SerializedBy annotation = classOfT.getAnnotation(SerializedBy.class);

        if (annotation == null) {
            throw new RuntimeException("");
        }

        Class<? extends JsonDeserializer> deserializer = annotation.deserializer();
        JsonDeserializer jsonDeserializer = JsonStorage.deserializers.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(deserializer.getSimpleName()))
                .findFirst()
                .get()
                .getValue();

        return (T) jsonDeserializer.deserialize(json);

    }

    @Override
    public String toJsonField(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        String newFieldName = "";
        Object newFieldValue = null;
        for (Field field : fields) {
            JsonValue annotation = field.getAnnotation(JsonValue.class);
            if (annotation != null) {
                field.setAccessible(true);
                if (!annotation.fieldName().equals("")) {
                    newFieldName = annotation.fieldName();
                } else {
                   newFieldName = field.getName();
                }
                try {
                    newFieldValue = field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "{\"fieldName\":\"" + newFieldName + "\",\"fieldValue\":" + "\"" + newFieldValue + "\"}";
    }

    @Override
    public <T> T fromJsonField(String json, Class<T> classOfT) {

        Object object = null;
        try {
            object = classOfT.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        String withoutBrackets = json.replace("{", "").replace("}", "");
        String withoutQuotes = withoutBrackets.replace("\"", "");
        String[] split = withoutQuotes.split(",");
        String[] splitValue = split[1].split(":");
        Field[] fields = classOfT.getDeclaredFields();
        for (Field field : fields) {
            JsonValue annotation = field.getAnnotation(JsonValue.class);
            if (annotation!=null) {
                field.setAccessible(true);
                try {
                    field.set(object,  parseInt(splitValue[1]));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return  (T) object;
    }
}
