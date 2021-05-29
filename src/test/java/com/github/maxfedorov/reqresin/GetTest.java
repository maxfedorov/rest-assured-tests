package com.github.maxfedorov.reqresin;

import com.github.maxfedorov.reqresin.models.Resources;
import com.github.maxfedorov.reqresin.models.Support;
import com.github.maxfedorov.reqresin.models.User;
import com.github.maxfedorov.reqresin.models.UserData;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@DisplayName("Get")
public class GetTest extends RestApiTestBase {

    @Test
    @Feature("Rest API")
    @Story("GET")
    @DisplayName("List users")
    void listUsers() {
        restGiven()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", is(2))
                .body("total", is(12))
                .body("support.text", containsString("ReqRes"))
                .body(matchesJsonSchemaInClasspath("jsonshemas/users.json"));
    }

    @Test
    @Feature("Rest API")
    @Story("GET")
    @DisplayName("Get single user")
    void getSingleUser() {
        User user = restGiven()
                .when()
                .get("/api/users/5")
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract().as(User.class);
        SoftAssertions softAssertions = new SoftAssertions();
        UserData data = user.getData();
        softAssertions.assertThat(data.getId()).isEqualTo(5);
        softAssertions.assertThat(data.getEmail()).isEqualTo("charles.morris@reqres.in");
        softAssertions.assertThat(data.getFirstName()).isEqualTo("Charles");
        softAssertions.assertThat(data.getLastName()).isEqualTo("Morris");
        softAssertions.assertThat(data.getAvatar()).isEqualTo("https://reqres.in/img/faces/5-image.jpg");
        Support support = user.getSupport();
        softAssertions.assertThat(support.getUrl()).isEqualTo("https://reqres.in/#support-heading");
        softAssertions.assertThat(support.getText()).isEqualTo("To keep ReqRes free, contributions towards server costs are appreciated!");
        softAssertions.assertAll();
    }

    @Test
    @Feature("Rest API")
    @Story("GET")
    @DisplayName("Get non existing user")
    void getNonExistingUser() {
        restGiven()
                .when()
                .get("/api/users/100")
                .then()
                .statusCode(404);
    }

    @Test
    @Feature("Rest API")
    @Story("GET")
    @DisplayName("List resources")
    void listResources() {
        Resources resource = restGiven()
                .when()
                .get("/api/unknown")
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract().as(Resources.class);
        Assertions.assertThat(resource.getData()).isNotEmpty();
    }

    @Test
    @Feature("Rest API")
    @Story("GET")
    @DisplayName("List resource not found")
    void listResourceNotFound() {
        restGiven()
                .when()
                .get("/api/unknown/50")
                .then()
                .statusCode(404);
    }

    @Test
    @Feature("Rest API")
    @Story("GET")
    @DisplayName("List resource")
    void listResource() {
        restGiven()
                .when()
                .get("/api/unknown/2")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonshemas/resource.json"));
    }

}
