package org.com.restassured.hamcrest;

import io.restassured.path.xml.element.Node;
import org.com.restassured.utils.RestAssuredConfigPath;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class WorkWithXmltoApiTest {
    @BeforeClass
    public static void configRestAssured() {
        RestAssuredConfigPath restAssuredConfigPath = new RestAssuredConfigPath("API_URL", "");
        baseURI = restAssuredConfigPath.getBaseUrl();
        basePath = restAssuredConfigPath.getBasePath();
    }

    @Test
    public void testingXmlResponse() {
        given()
                .when()
                    .get("usersXML/3")
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
                    .get("usersXML/")
                .then()
                    .statusCode(200)
                    .body("users.user.size()", is(3))
                    .body("users.user.findAll{it.age.toInteger() <= 25}.size()", is(2))
                    .body("users.user.@id", hasItems("1", "2", "3"))
                    .body("users.user.find{it.age == 25}.name", is("Maria Joaquina"))
                    .body("users.user.findAll{it.name.toString().contains('n')}.name", hasItems("Maria Joaquina", "Ana Julia"))
                    .body("users.user.salary.find{it != null}.toDouble()", is(1234.5678d))
                    .log().all();

    }

    @Test
    public void testingAdvancedVerificationXmlTestWithJava() {
        String name = given()
                .when()
                    .get("usersXML/")
                .then()
                .statusCode(200)
                .extract().path("users.user.name.findAll{it.toString().startsWith('Maria')}");

        System.out.println(name);

        Assert.assertEquals("Maria Joaquina".toUpperCase(), name.toUpperCase());

        ArrayList<Node> names = given()
                .when()
                .get("usersXML/")
                .then()
                .statusCode(200)
                .extract().path("users.user.name.findAll{it.toString().contains('n')}");

        System.out.println(names);

        Assert.assertEquals(2, names.size());
        Assert.assertEquals("Maria Joaquina".toUpperCase(), names.get(0).toString().toUpperCase());

        given()
                .when()
                    .get("usersXML/")
                .then()
                    .statusCode(200)
                    .body(hasXPath("count(/users/user)", is("3")))
                    .body(hasXPath("/users/user[@id = '1']"))
                    .body(hasXPath("//user[@id = '2']"))
                    //.body(hasXPath("/users/user/name"), is("João da Silva"))
                    .log().all();
    }
}
