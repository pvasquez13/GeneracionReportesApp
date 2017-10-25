package com.globokas.utils;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author pvasquez
 */
public class ConfigApp {
    
    private static final String PROPERTIES_FILENAME = "config_generico.properties";
    private static Properties config;

    static {
        try {
            config = new Properties();
//            InputStream inputStream = ConfigApp.class.getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
            FileInputStream inputStream = new FileInputStream("./"+PROPERTIES_FILENAME);
            config.load(inputStream);
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo cargar el archivo " + PROPERTIES_FILENAME, e);
        }
    }

    public static String getValue(String key) {

        return config.getProperty(key);
    }

    public static Enumeration getPropertyNames() {

        return config.propertyNames();
    }
    
}
