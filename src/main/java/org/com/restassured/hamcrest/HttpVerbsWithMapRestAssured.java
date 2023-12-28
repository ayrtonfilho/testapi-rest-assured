package org.com.restassured.hamcrest;

import org.com.restassured.generators.UsersGeneratedTest;
import org.com.restassured.interfaces.User;
import org.com.restassured.utils.RestAssuredConfigPath;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class HttpVerbsWithMapRestAssured {

    @BeforeClass
    public static void configRestAssured() {
        RestAssuredConfigPath restAssuredConfigPath = new RestAssuredConfigPath("API_URL", "");
        baseURI = restAssuredConfigPath.getBaseUrl();
        basePath = restAssuredConfigPath.getBasePath();
    }

    @Test
    public void saveUserJsonTest() {

        Map<String, Object> params = new HashMap<>();
        String userName = UsersGeneratedTest.gerarNomeAleatorio();

        params.put("name", userName);
        params.put("age", 25);

        given()
                .contentType("application/json")
                .body(params)
                .when()
                .post("users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .body("name", is(userName))
                .body("age", is(25));
    }

    @Test
    public void saveUserJsonObjectTest() {
        String userName = UsersGeneratedTest.gerarNomeAleatorio();

        User user = new User(userName, 25);

        User userInsert =
                given()
                .contentType("application/json")
                .body(user)
                .when()
                    .post("users")
                .then()
                    .log().all()
                        .statusCode(201)
                        .extract().body().as(User.class);

        Assert.assertEquals(userName, userInsert.getName());
        Assert.assertEquals(25, userInsert.getAge());
        Assert.assertNotEquals(null, userInsert.getId());
    }
}
