package org.level.up.json;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface SerializedBy {

    Class<? extends JsonSerializer> serializer();

    Class<? extends JsonDeserializer> deserializer();

}
