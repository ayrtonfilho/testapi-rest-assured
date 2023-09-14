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

            return  properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
