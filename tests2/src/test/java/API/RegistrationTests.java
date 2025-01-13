package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import java.time.LocalDate;

public class RegistrationTests {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @Test
    void successfulRegistrationTest() {
        String requestBody = """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "pistol"
                }
                """;

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void registrationWithoutPasswordTest() {
        String requestBody = """
                {
                    "email": "eve.holt@reqres.in"
                }
                """;

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    void getUsersEmailsTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data.email", everyItem(endsWith("@reqres.in")));
    }
    @Test
    void deleteUserTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void updateUserTest() {
        String requestBody = """
            {
                "name": "John",
                "job": "Developer"
            }
            """;

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .body("updatedAt", startsWith(LocalDate.now().toString()));
    }
}