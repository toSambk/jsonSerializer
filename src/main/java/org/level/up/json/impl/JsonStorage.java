package org.level.up.json.impl;

import org.level.up.json.JsonDeserializer;
import org.level.up.json.JsonSerializer;
import org.level.up.json.configuration.JsonConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonStorage {

    public static Map<String, JsonSerializer> serializers;
    public static Map<String, JsonDeserializer> deserializers;

    static {
        try {
            loadClasses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadClasses() throws IOException {
        Collection<Class<?>> classes = scanDirectory();
        serializers = createMap(classes, JsonSerializer.class);
        deserializers = createMap(classes, JsonDeserializer.class);
    }

    private static <T> Map<String, T> createMap(Collection<Class<?>> classes, Class<T> certainClass) {
        //noinspection unchecked
        return classes.stream()
                .filter(clazz -> {
                    Class<?>[] interfaces = clazz.getInterfaces();
                    return Arrays.stream(interfaces)
                            .anyMatch(c -> c == certainClass);
                })
                .collect(Collectors.toMap(Class::getSimpleName, clazz -> (T) newInstance(clazz)));
    }

    private static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Collection<Class<?>> scanDirectory() throws IOException {
        String filepath = JsonConfiguration.getInstance().getConfiguration().getFilepath();
        return Files.walk(Paths.get(filepath))
                .map(Path::toFile)
                .filter(File::isFile)
                .map(File::toString)
                .map(filename -> filename.replace(filepath, "").replace("/", ".").replace("\\", ".").replace(".java", ""))
                .filter(filename -> !filename.isEmpty())
                .map(JsonStorage::loadClass)
                .collect(Collectors.toList());
    }

    private static Class<?> loadClass(String fullName) {
        try {
            return Class.forName(fullName);
        } catch (ClassNotFoundException exc) {
            throw new RuntimeException(exc);
        }
    }

}
