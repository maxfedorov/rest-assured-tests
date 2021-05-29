package com.github.maxfedorov.reqresin;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Post")
public class PostTest extends RestApiTestBase {

    @Test
    @Feature("Rest API")
    @Story("POST")
    @DisplayName("Create user")
    void createUser() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Alex");
        requestParams.put("job", "Driver");
        restGiven()
                .contentType(JSON)
                .body(requestParams.toString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", is(requestParams.get("name")))
                .body("job", is(requestParams.get("job")));
    }

    @Test
    @Feature("Rest API")
    @Story("POST")
    @DisplayName("Success login")
    void successLogin() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "cityslicka");
        restGiven()
                .contentType(JSON)
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @Feature("Rest API")
    @Story("POST")
    @DisplayName("Failed login")
    void failedLogin() {
        JSONObject requestParams = new JSONObject().put("email", "eve.holt@reqres.in");
        restGiven()
                .contentType(JSON)
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @Feature("Rest API")
    @Story("POST")
    @DisplayName("Successful registration")
    void successfulRegistration() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "pistol");
        restGiven()
                .contentType(JSON)
                .body(requestParams.toString())
                .when()
                .post("/api/registration")
                .then()
                .log()
                .body()
                .statusCode(201)
                .body("id", notNullValue());
    }

}
