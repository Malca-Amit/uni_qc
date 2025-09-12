package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static String getProperty(String key) {
        if (prop == null) {
            try {
                prop = new Properties();
                FileInputStream fis = new FileInputStream("src/main/java/config/Config.properties");
                prop.load(fis);
            } catch (IOException e) {
                e.printStackTrace(); // Optional: you can log it instead
                throw new RuntimeException("Failed to load configuration file", e);
            }
        }
        return prop.getProperty(key);
    }
}
