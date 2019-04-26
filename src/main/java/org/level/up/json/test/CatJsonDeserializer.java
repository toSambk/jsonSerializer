package org.level.up.json.test;
import org.level.up.json.JsonDeserializer;
import java.lang.reflect.Field;
import java.util.Arrays;

public class CatJsonDeserializer implements JsonDeserializer<Cat> {

    @Override
    public Cat deserialize(String json) {

        String withoutBrackets = json.replace("{", "").replace("}", "");
        String withoutQuotes = withoutBrackets.replace("\"", "");
        String[] split = withoutQuotes.split(",");

        Cat cat = new Cat();

        Arrays.stream(split)
                .forEach(fieldValue -> {
                    String[] object = fieldValue.split(":");
                    setupField(cat, object);
                });

        return cat;
    }


    private void setupField(Cat cat, String[] object) {

        try {
            Class<?> catClass = cat.getClass();
            Field field = catClass.getDeclaredField(object[0]);
            field.setAccessible(true);
            if (field.getType() == int.class) {
                setupIntField(cat, field, object[1]);
            } else {
                field.set(cat, object[1]);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupIntField(Cat cat, Field field, String value) throws IllegalAccessException {
        field.set(cat, Integer.parseInt(value));
    }
}