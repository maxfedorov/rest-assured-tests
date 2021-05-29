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
public class PutTest extends RestApiTestBase {

    @Test
    @Feature("Rest API")
    @Story("PUT")
    @DisplayName("Update user")
    void updateUser() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "John");
        requestParams.put("job", "Accountant");
        restGiven()
                .contentType(JSON)
                .body(requestParams.toString())
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .body("name", is(requestParams.get("name")))
                .body("job", is(requestParams.get("job")))
                .body("updatedAt", notNullValue());
    }
}
