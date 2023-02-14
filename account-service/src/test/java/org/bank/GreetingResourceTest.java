package org.bank;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GreetingResourceTest {

    @Test
    @Order(1)
    void testGetAccountsEndpoint() {
        Response result =
        given()
          .when().get("/api/v1/accounts")
          .then()
             .statusCode(200)
             .body(
                     containsString("George Baird"),
                     containsString("Mary Taylor"),
                     containsString("Diana Rigg")
             )
                .extract()
                .response();
        List<Account> accounts = result.jsonPath().getList("$");
        Assertions.assertNotEquals(Collections.emptyList(), accounts);
    }

}