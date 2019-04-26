package org.level.up.json.configuration;

/**
 * Class for storing different properties (settings) of application.
 *
 * @author protsko on 19.04.2019
 */
public class Configuration {

    /**
     * Filepath, in which application will search classes
     * (serializers and deserializers)
     *
     * @see org.level.up.json.JsonSerializer
     * @see org.level.up.json.JsonDeserializer
     */
    private String filepath;

    Configuration(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

}
