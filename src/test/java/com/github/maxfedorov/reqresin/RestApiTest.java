package com.github.maxfedorov.reqresin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RestApiTest {

    @BeforeAll
    static void setup() {
        baseURI = "https://reqres.in";
    }

    @Test
    void listUsers() {
        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", is(2))
                .body("total", is(12))
                .body("support.text", containsString("ReqRes"));
    }

    @Test
    void successLogin() {
        given()
                .contentType(JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", " +
                        "\"password\": \"cityslicka\" }")
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void failedLogin() {
        given()
                .contentType(JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}
