package com.github.maxfedorov.reqresin;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Delete")
public class DeleteTest {

    @Test
    @Feature("Rest API")
    @Story("DELETE")
    @DisplayName("Delete user")
    void updateUser() {
        new Specs().request()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

}
