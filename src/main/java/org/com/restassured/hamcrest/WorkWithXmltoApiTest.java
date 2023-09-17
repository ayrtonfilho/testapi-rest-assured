package org.com.restassured.hamcrest;

import org.com.restassured.utils.GetEnvironmentUtils;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class WorkWithXmltoApiTest {
    private static final GetEnvironmentUtils getEnvironmentUtils = new GetEnvironmentUtils();
    public static final String XML_URL = getEnvironmentUtils.getApplicationProperties("XML_URL");

    @Test
    public void testingXmlResponse() {
        given()
                .when()
                    .get(XML_URL + 3)
                .then()
                    .rootPath("user")
                        .statusCode(200)
                        .body("name", is("Ana Julia"))
                        .body("@id", is("3"))
                    .rootPath("user.filhos") // troca o valor de rootPath
                        .body("name.size()", is(2))
                        .body("name", hasItem("Luizinho"))
                        .body("name", hasItems("Luizinho", "Zezinho"))
                    .detachRootPath("filhos") // remove "filhos" de rootPath
                        .body("filhos.name.size()", is(2))
                        .body("filhos.name", hasItem("Luizinho"))
                        .body("filhos.name", hasItems("Luizinho", "Zezinho"))
                    .appendRootPath("filhos") // adiciona "filhos" ao rootPath
                        .body("name.size()", is(2))
                        .body("name", hasItem("Luizinho"))
                        .body("name", hasItems("Luizinho", "Zezinho"))
                    .log().all();
    }

    @Test
    public void testingAdvancedVerificationXmlTest() {
        given()
                .when()
                    .get(XML_URL)
                .then()
                    .statusCode(200)
                    .body("users.user.size()", is(3))
                .body("user.user.findAll{it.age.toInteger() <= 25}.size()", is(2));
    }
}
