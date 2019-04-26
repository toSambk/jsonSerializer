package org.level.up.json.configuration;

/**
 * @author protsko on 19.04.2019
 */
public class JsonConfiguration {

    private static final JsonConfiguration instance = new JsonConfiguration();

    private Configuration configuration;

    private JsonConfiguration() {
        this.configuration = JsonConfigurationLoader.loadConfiguration();
    }

    public static JsonConfiguration getInstance() {
        return instance;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
