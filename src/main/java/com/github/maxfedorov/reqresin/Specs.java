package com.github.maxfedorov.reqresin;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.github.maxfedorov.reqresin.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Specs {
    public RequestSpecification request() {
        return with()
                .baseUri("https://reqres.in")
                .basePath("/api")
                .filter(customLogFilter().withCustomTemplates())
                .contentType(JSON)
                .log().all();
    }

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
