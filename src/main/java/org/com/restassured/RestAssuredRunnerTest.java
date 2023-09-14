package org.com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.com.restassured.utils.GetEnvironmentUtils;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class RestAssuredRunnerTest {
    private static final GetEnvironmentUtils getEnvironmentUtils = new GetEnvironmentUtils();
    public static final String HELLO_URL = getEnvironmentUtils.getApplicationProperties("HELLO_URL");

    @Test
    public void testStatusCodeResponse() {


        Response response = RestAssured.request(Method.GET, HELLO_URL);
        System.out.println("RESPONSE STATUS CODE: " + response.getStatusCode());

        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void testContentResponseBody() {
        Response response = RestAssured.request(Method.GET, HELLO_URL);

        System.out.println("RESPONSE BODY: " + response.getBody().asString());

        Assert.assertEquals("Ola Mundo!", response.getBody().asString());
    }

    @Test
    public void testGivenSimpleMethod() {
        given()
                .when()
                .get(HELLO_URL)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    public static void main(String[] args) {
        System.out.println("Learning about JUnit and RestAssured!");
    }
}