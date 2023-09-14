package org.com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.com.restassured.utils.PropertyUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunnerTestRestAssured {

    @Test
    public void testMainMethod() {
        PropertyUtils propertyUtils = new PropertyUtils();
        String URI_API = propertyUtils.getApplicationProperties("URI_API");

        Response response = RestAssured.request(Method.GET, URI_API);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

        Assertions.assertEquals(200, response.statusCode());
    }

    public static void main(String[] args) {
        System.out.println("Learning about JUnit and RestAssured!");
    }
}