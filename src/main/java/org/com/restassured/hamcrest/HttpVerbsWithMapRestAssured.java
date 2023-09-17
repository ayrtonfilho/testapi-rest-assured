package org.com.restassured.hamcrest;

import org.com.restassured.interfaces.User;
import org.com.restassured.utils.RestAssuredConfigPath;
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
        params.put("name", "Maria Joaquina");
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
                .body("name", is("Maria Joaquina"))
                .body("age", is(25));
    }

    @Test
    public void saveUserJsonObjectTest() {
        User user = new User("Maria Joaquina", 25);

        given()
                .contentType("application/json")
                .body(user)
                .when()
                    .post("users")
                .then()
                    .log().all()
                    .statusCode(201)
                    .body("id", is(notNullValue()))
                    .body("name", is("Maria Joaquina"))
                    .body("age", is(25));
    }
}
