package org.com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class RunnerTestRestAssured {

    @Test
    public static void main(String[] args) {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me:80/ola");

        System.out.println(response.getBody().asString().equals("Ola Mundo!"));
        System.out.println(response.statusCode() == 200);

        ValidatableResponse validacao = response.then();
        validacao.statusCode(201);

    }
}