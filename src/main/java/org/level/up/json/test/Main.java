package org.level.up.json.test;

import org.level.up.json.JsonService;
import org.level.up.json.impl.JsonServiceImpl;

import java.util.Arrays;

public class Main {

    public static void main (String[] args) {
        JsonService service = new JsonServiceImpl();

        Cat cat = new Cat ("cat", 12);
        //String json = service.toJson(cat);
        //System.out.println(json);
        //Cat fromJson = service.fromJson(json, Cat.class);
        //System.out.println(fromJson.getName() + " " + fromJson.getAge());
        System.out.println("Working with fields:");
        String jsonField = service.toJsonField(cat);
        System.out.println(jsonField);
        Cat fromJsonField = service.fromJsonField("{\"fieldName\":\"jsonValue\",\"fieldValue\":15}", Cat.class);
        System.out.println(/*"Field of object: " + Arrays.stream(fromJsonField
                .getJsonValue()
                .getClass()
                .getDeclaredFields())
                .findFirst()
                .get()
                .getName() + */"\tValue of object: " + fromJsonField.getJsonValue());

        System.out.println(1/5);
    }

}
