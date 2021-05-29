package com.github.maxfedorov.reqresin;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Put")
public class PutTest {

    @Test
    @Feature("Rest API")
    @Story("PUT")
    @DisplayName("Update user")
    void updateUser() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "John");
        requestParams.put("job", "Accountant");
        new Specs().request()
                .contentType(JSON)
                .body(requestParams.toString())
                .when()
                .put("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .body("name", is(requestParams.get("name")))
                .body("job", is(requestParams.get("job")))
                .body("updatedAt", notNullValue());
    }
}
