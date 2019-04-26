package org.level.up.json.test;
import org.level.up.json.JsonValue;
import org.level.up.json.SerializedBy;

@SerializedBy(
        serializer = CatJsonSerializer.class,
        deserializer = CatJsonDeserializer.class
)


public class Cat {

    private String name ;
    private int age;
    @JsonValue(fieldName = "")
    private int jsonValue = 7;

    public Cat(){}

    public Cat (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJsonValue() {
        return jsonValue;
    }
}
