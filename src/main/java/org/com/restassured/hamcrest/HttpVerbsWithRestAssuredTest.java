package org.com.restassured.hamcrest;

import io.restassured.http.ContentType;
import org.com.restassured.utils.RestAssuredConfigPath;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HttpVerbsWithRestAssuredTest {

    @BeforeClass
    public static void configRestAssured() {
        RestAssuredConfigPath restAssuredConfigPath = new RestAssuredConfigPath("API_URL", "");
        baseURI = restAssuredConfigPath.getBaseUrl();
        basePath = restAssuredConfigPath.getBasePath();
    }

    @Test
    public void saveUserJsonTest() {
        given()
                .contentType("application/json")
                .body("{\"name\": \"Maria Joaquina\", \"age\": 25}")
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
    public void dontSaveUserJsonNullNameTest() {
        given()
                .contentType("application/json")
                .body("{\"age\": 25}")
                .when()
                    .post("users")
                .then()
                    .log().all()
                    .statusCode(400)
                    .body("id", is(nullValue()))
                    .body("error", is("Name é um atributo obrigatório"));
    }

    @Test
    public void saveUserXmlTest() {
        given()
                .contentType(ContentType.XML)
                .body("<user><name>Maria Joaquina</name><age>25</age></user>")
                .when()
                    .post("usersXML/")
                .then()
                    .log().all()
                    .statusCode(201)
                    .body("user.@id", is(notNullValue()))
                    .body("user.name", is("Maria Joaquina"))
                    .body("user.age", is("25"));
    }

    @Test
    public void updateUserJsonTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Maria Joaquina\", \"age\": 80}")
                .when()
                    .put("users/1")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("name", is("Maria Joaquina"))
                    .body("age", is(80));
    }

    @Test
    public void customizationUrlUserJsonTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Maria Joaquina\", \"age\": 80}")
                .pathParams("entidade", "users")
                .pathParams("code", "1")
                .when()
                    .put("{entidade}/{code}")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("name", is("Maria Joaquina"))
                    .body("age", is(80));
    }

    @Test
    public void removeUserJsonTest() {
        given()
                .log().all()
                    .contentType(ContentType.JSON)
                .body("{\"name\": \"Maria Joaquina\", \"age\": 80}")
                .pathParams("entidade", "users")
                .pathParams("code", "1")
                .when()
                    .delete("{entidade}/{code}")
                .then()
                    .log().all()
                    .statusCode(204);
    }

    @Test
    public void dontRemoveUserJsonTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Maria Joaquina\", \"age\": 80}")
                .pathParams("entidade", "users")
                .pathParams("code", "100")
                .when()
                    .delete("{entidade}/{code}")
                .then()
                    .log().all()
                    .statusCode(204)
                    .body("error", is("Registro inexistente"));
    }
}
