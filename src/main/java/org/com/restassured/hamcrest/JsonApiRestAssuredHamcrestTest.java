package org.com.restassured.hamcrest;

import static io.restassured.RestAssured.*;

import org.com.restassured.utils.GetEnvironmentUtils;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import java.util.Arrays;

public class JsonApiRestAssuredHamcrestTest {
    private static final GetEnvironmentUtils getEnvironmentUtils = new GetEnvironmentUtils();
    public static final String USERS_URL = getEnvironmentUtils.getApplicationProperties("USERS_URL");
    @Test
    public void firstLevelVerification() {

        given()
                .when()
                    .get(USERS_URL + 1)
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
                    .get(USERS_URL + 2)
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
                    .get(USERS_URL + 3)
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
                    .get(USERS_URL + 4)
                .then()
                    .statusCode(404)
                    .log().all()
                    .body("error", containsString("Usuário inexistente"));
    }

    @Test
    public void fifthLevelListVerification() {
        given()
                .when()
                    .get(USERS_URL)
                .then()
                    .statusCode(200)
                    .log().all()
                .body("name", hasItems("Maria Joaquina", "Ana Júlia", "João da Silva"))
                .body("age[1]", is(25))
                .body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho")))
                .body("salary", contains(1234.5678f, 2500, null));
    }
}
