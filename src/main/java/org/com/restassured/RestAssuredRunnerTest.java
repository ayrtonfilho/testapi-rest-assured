package org.com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

import org.com.restassured.utils.RestAssuredConfigPath;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class RestAssuredRunnerTest {
    @BeforeClass
    public static void configRestAssured() {
        RestAssuredConfigPath restAssuredConfigPath = new RestAssuredConfigPath("API_URL", "");
        baseURI = restAssuredConfigPath.getBaseUrl();
        basePath = restAssuredConfigPath.getBasePath();
    }

    @Test
    public void testStatusCodeResponse() {


        Response response = RestAssured.request(Method.GET, "ola");
        System.out.println("RESPONSE STATUS CODE: " + response.getStatusCode());

        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void testContentResponseBody() {
        Response response = RestAssured.request(Method.GET, "ola");

        System.out.println("RESPONSE BODY: " + response.getBody().asString());

        Assert.assertEquals("Ola Mundo!", response.getBody().asString());
    }

    @Test
    public void testGivenSimpleMethod() {
        given()
                .when()
                .get("ola")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    public static void main(String[] args) {
        System.out.println("Learning about JUnit and RestAssured!");
    }
}