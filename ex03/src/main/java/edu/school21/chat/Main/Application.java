package edu.school21.chat.Main;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Application {
    private static final Properties PROPERTIES = new Properties();

    private Application() {

    }

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (var file =  new FileReader("src/main/resources/application.properties")) {
            PROPERTIES.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
