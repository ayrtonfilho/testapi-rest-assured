package org.com.restassured.hamcrest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.com.restassured.RestAssuredRunnerTest.HELLO_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class RestAssuredHamcrestTest {
    @Test
    public void validateBodyResponse() {
        given()
                .when()
                    .get(HELLO_URL)
                .then()
                    .statusCode(200)
                    .body(is("Ola Mundo!"))
                    .body(containsString("Mundo"))
                    .body(is(not(nullValue())))
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
}
