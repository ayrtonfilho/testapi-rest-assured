package org.com.restassured.hamcrest;

import org.com.restassured.utils.RestAssuredConfigPath;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class SerializationAndDeserializationXmlTest {

    @BeforeClass
    public static void configRestAssured() {
        RestAssuredConfigPath restAssuredConfigPath = new RestAssuredConfigPath("API_URL", "");
        baseURI = restAssuredConfigPath.getBaseUrl();
        basePath = restAssuredConfigPath.getBasePath();
    }

    @Test
    public void testSerializationAndDeserializationXml() {

    }
}
