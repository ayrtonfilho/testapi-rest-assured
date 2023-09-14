package org.com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.com.restassured.utils.GetEnvironmentUtils;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredRunnerTest {
    private final GetEnvironmentUtils getEnvironmentUtils = new GetEnvironmentUtils();
    private final String API_URI = getEnvironmentUtils.getApplicationProperties("API_URI");
    @Test
    public void testStatusCodeResponse() {


        Response response = RestAssured.request(Method.GET, API_URI);
        System.out.println("RESPONSE STATUS CODE: " + response.getStatusCode());

        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void testContentResponseBody() {
        Response response = RestAssured.request(Method.GET, API_URI);

        System.out.println("RESPONSE BODY: " + response.getBody().asString());

        Assert.assertEquals("Ola Mundo!", response.getBody().asString());
    }

    @Test
    public void testGivenSimpleMethod() {
        given()
                .when()
                .get(API_URI)
                .then()
                .statusCode(200)
                .log()
                .all();
    }
    @Test
    public void testAssertionsHamcrest() {
        MatcherAssert.assertThat("Olá Mundo!", Matchers.is("Olá Mundo!"));
        MatcherAssert.assertThat(128, Matchers.is(128));

        assertThat("Maria", is(not("João")));
        assertThat("Maria", is(not("João")));
    }

    @Test
    public void arrayAssertionsHamcrest() {
        List<Integer> RunnerTestRestAssured = Arrays.asList(1, 3, 5, 7, 9);

        assertThat(RunnerTestRestAssured, contains(1, 3, 5, 7, 9));
    }

    public static void main(String[] args) {
        System.out.println("Learning about JUnit and RestAssured!");
    }
}