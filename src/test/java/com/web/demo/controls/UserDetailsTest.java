package com.web.demo.controls;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserDetailsTest {

    //https://qaautomation.expert/2021/07/26/integration-testing-of-springboot-using-restassured/
    //https://rieckpil.de/testing-spring-boot-applications-with-rest-assured/#:~:text=REST%20Assured%20is%20a%20Java,as%20it%20is%20framework%2Dindependent.

    int userId;

    @Test(priority = 1)
    void getUserByPage() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("total_pages",equalTo(2))
                .log()
                .all();
    }

    @Test(priority = 2)
    void createUserTest() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Hari");
        map.put("job", "Reddy");

        given()
                .contentType("application/json")
                .body(map)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log()
                .all();

    }

    @Test(priority = 3)
    void createUserTestWithId() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Hari");
        map.put("job", "Reddy");

        userId = given()
                .contentType("application/json")
                .body(map)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

    }

    @Test(priority = 4, dependsOnMethods = {"createUserTestWithId"})
    void updateUserTest() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Bablu");
        map.put("job", "Reddy");

        given()
                .contentType("application/json")
                .body(map)
                .when()
                .put("https://reqres.in/api/users/" + userId)
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(priority = 5,dependsOnMethods = {"createUserTestWithId"})
    void deleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/" + userId)
                .then()
                .statusCode(204)
                .log()
                .all();
    }
}
