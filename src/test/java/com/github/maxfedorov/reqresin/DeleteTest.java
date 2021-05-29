package com.github.maxfedorov.reqresin;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Delete")
public class DeleteTest extends RestApiTestBase {

    @Test
    @Feature("Rest API")
    @Story("DELETE")
    @DisplayName("Delete user")
    void updateUser() {
        restGiven()
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }

}
