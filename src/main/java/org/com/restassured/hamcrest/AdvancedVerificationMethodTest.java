package org.com.restassured.hamcrest;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.com.restassured.hamcrest.JsonApiRestAssuredHamcrestTest.USERS_URL;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class AdvancedVerificationMethodTest {

    @Test
    public void getAllCollectionUsersTest() {
        given()
                .when()
                    .get(USERS_URL)
                .then()
                    .statusCode(200)
                    .body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina")) // usuários que tenha idade menor ou igual a 25 e maior que 20 e com nome "Maria Joaquina"
                    .body("findAll{it.age <= 25 && it.age > 20}[0].name", is("Maria Joaquina"))
                    .body("findAll{it.age <= 25}[-1].name", is("Ana Júlia"))
                    .body("find{it.name.contains('n')}.name", is("Maria Joaquina"))
                    .body("findAll{it.name.length() > 10}.name", hasItems("João da Silva", "Maria Joaquina"))
                    .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))
                    .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", allOf(arrayContaining("MARIA JOAQUINA"),  arrayWithSize(1)))
                    .log().all();
    }

    @Test
    public void getAllCollectionUsersQuantityTest() {
        given()
                .when()
                .get(USERS_URL)
                .then()
                .statusCode(200)
                .body("$", hasSize(3)) //garantia de que a coleção terá 3 registros brutos
                .body("age.findAll{it <= 25}.size()", is(2))
                .body("age.findAll{it <= 25 && it > 20}.size()", is(1))
                .body("id.max()", is(3))
                .body("salary.min()", is(1234.5678f))
                .body("salary.findAll{it != null}.sum()", is(closeTo(3734.5678f, 0.001)))
                .body("salary.findAll{it != null}.sum()", allOf(greaterThan(3000d), lessThan(5000d)))
                .log().all();
    }

    @Test
    public void shouldJoinJsonPathWithJavaTest() {
        ArrayList<String> names =
                given()
                    .when()
                    .get(USERS_URL)
                    .then()
                    .statusCode(200)
                    .extract().path("name.findAll({it.startsWith('Maria')})");

        Assert.assertEquals(1, names.size());
        Assert.assertEquals(names.get(0), equalToIgnoringCase("Maria Joaquina"));
        Assert.assertEquals(names.get(0).toUpperCase(), "maria joaquina".toUpperCase());
    }
}