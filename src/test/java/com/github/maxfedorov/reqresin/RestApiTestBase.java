package com.github.maxfedorov.reqresin;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static com.github.maxfedorov.reqresin.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RestApiTestBase {

    @BeforeAll
    static void setup() {
        baseURI = "https://reqres.in";
    }

    protected RequestSpecification restGiven() {
        return given().filter(customLogFilter().withCustomTemplates());
    }
}
