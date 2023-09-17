package org.com.restassured.utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfigPath {
    private final String baseUrl;
    private final String basePath;

    public RestAssuredConfigPath(String environmentUrl, String basePath) {
        this.baseUrl = new GetEnvironmentUtils().getApplicationProperties(environmentUrl);
        this.basePath = basePath;
        configureRestAssured();
    }

    private void configureRestAssured() {
        RestAssured.baseURI = this.baseUrl;
        RestAssured.basePath = this.basePath;
    }

    public RequestSpecification given() {
        return RestAssured.given();
    }

    public String getBasePath() {
        return basePath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}

