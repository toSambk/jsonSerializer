package org.level.up.json.test;
import org.level.up.json.JsonSerializer;

public class CatJsonSerializer implements JsonSerializer<Cat> {

    @Override
    public String serialize(Cat object) {
        return "{\"name\":\"" + object.getName() + "\",\"age\":" + object.getAge() + "}";
    }

}
