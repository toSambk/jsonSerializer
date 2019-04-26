package org.level.up.json;

// {
//      "name": "Cat",
//      "age": 12,
//      "properties": [
//          "color"
//      ]
// }

public interface JsonSerializer<T> {

    String serialize(T object);

}
