package com.hrms.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    public static Properties properties;

    /**
     * This method reads any given property file
     *
     * @param filePath - location of configuration file
     * @return Properties
     */

    public static Properties readConfiguration(String filePath) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    /**
     * This method retrieves a single value based on the specified key
     *
     * @param key -
     * @return String
     */

    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
