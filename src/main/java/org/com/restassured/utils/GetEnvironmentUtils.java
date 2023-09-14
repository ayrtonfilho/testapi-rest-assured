package org.com.restassured.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetEnvironmentUtils {
    public String getApplicationProperties(String property) {
        try {
            Properties properties = new Properties();
            InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);

            String value = properties.getProperty(property);

            if (value == null || value.trim().isEmpty()) {
                throw new IOException("Property not found: " + property);
            }

            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
