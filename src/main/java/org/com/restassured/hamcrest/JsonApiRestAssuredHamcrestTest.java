package org.com.restassured.hamcrest;

import static io.restassured.RestAssured.*;

import org.com.restassured.utils.GetEnvironmentUtils;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class JsonApiRestAssuredHamcrestTest {
    private static final GetEnvironmentUtils getEnvironmentUtils = new GetEnvironmentUtils();
    public static final String USERS_URL = getEnvironmentUtils.getApplicationProperties("USERS_URL");
    @Test
    public void firstLevelVerification() {

        given()
                .when()
                .get(USERS_URL)
                .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("name", containsString("Silva"))
                    .body("age", greaterThan(18))
                    .log().all();
    }
}
