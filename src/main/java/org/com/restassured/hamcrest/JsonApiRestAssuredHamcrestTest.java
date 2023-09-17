package org.com.restassured.hamcrest;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.com.restassured.utils.RestAssuredConfigPath;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class JsonApiRestAssuredHamcrestTest {
    @BeforeClass
    public static void configRestAssured() {
        RestAssuredConfigPath restAssuredConfigPath = new RestAssuredConfigPath("API_URL", "");
        baseURI = restAssuredConfigPath.getBaseUrl();
        basePath = restAssuredConfigPath.getBasePath();
    }
    @Test
    public void firstLevelVerification() {

        given()
                .when()
                    .get("users/1")
                .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("name", containsString("Silva"))
                    .body("age", greaterThan(18))
                    .log().all();
    }

    @Test
    public void secondLevelVerification() {
        given()
                .when()
                    .get("users/2")
                .then()
                    .statusCode(200)
                    .log().all()
                    .body("id", is(2))
                    .body("name", containsString("Joaquina"))
                    .body("endereco.rua", is("Rua dos bobos"))
                    .body("age", greaterThan(18));
    }

    @Test
    public void thirdLevelListVerification() {
        given()
                .when()
                    .get("users/3")
                .then()
                    .statusCode(200)
                    .log().all()
                    .body("id", is(3))
                    .body("filhos", hasSize(2))
                    .body("filhos[1].name", is("Luizinho"))
                    .body("filhos.name", hasItem("Zezinho"))
                    .body("filhos.name", hasItems("Zezinho", "Luizinho"));
    }

    @Test
    public void fourthLevelListVerification() {
        given()
                .when()
                    .get("users/4")
                .then()
                    .statusCode(404)
                    .log().all()
                    .body("error", containsString("Usuário inexistente"));
    }

    @Test
    public void fifthLevelListVerification() {
        given()
                .when()
                    .get("users/")
                .then()
                    .statusCode(200)
                    .log().all()
                .body("name", hasItems("Maria Joaquina", "Ana Júlia", "João da Silva"))
                .body("age[1]", is(25))
                .body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho")))
                .body("salary", contains(1234.5678f, 2500, null));
    }
}
