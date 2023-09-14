package org.com.restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.com.restassured.utils.GetEnvironmentUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunnerTestRestAssured {
    private final GetEnvironmentUtils getEnvironmentUtils = new GetEnvironmentUtils();
    @Test
    public void testStatusCodeResponse() {
        String URI_API = getEnvironmentUtils.getApplicationProperties("URI_API");

        Response response = RestAssured.request(Method.GET, URI_API);
        System.out.println("RESPONSE STATUS CODE: " + response.getStatusCode());

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void testContentResponseBody() {
        String URI_API = getEnvironmentUtils.getApplicationProperties("URI_API");

        Response response = RestAssured.request(Method.GET, URI_API);

        System.out.println("RESPONSE BODY: " + response.getBody().asString());

        Assertions.assertEquals("Ola Mundo!", response.getBody().asString());
    }

    @Test
    public void testGivenSimpleMethod() {
        String URI_API = getEnvironmentUtils.getApplicationProperties("URI_API");

        given()
                .when()
                .get(URI_API)
                .then()
                .statusCode(200)
                .log()
                .all();
    }
    public static void main(String[] args) {
        System.out.println("Learning about JUnit and RestAssured!");
    }
}