package com.web.demo.controls;

import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class EmployeeCrudAssuredTest {

    int id;

    @Test(priority = 1)
    void employeeListTest() {
        given()
                .when()
                .get("http://localhost:8081/empData/list")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", greaterThanOrEqualTo(320))
                .body("lastName[0]", equalTo("Lopez"))
                .body("yearsOfExperience[0]", equalTo(2))
                .body("department[0]", equalTo("Product"))
                .log()
                .all();
    }

    @Test(priority=2)
    void getEmployeeByIdTest() {
        given()
                .when()
                .get("http://localhost:8081/empData/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("lastName", equalTo("Lopez"))
                .body("yearsOfExperience", equalTo(2))
                .body("department", equalTo("Product"))
                .log()
                .all();
    }

    //@Test
    void testPostEmployee() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("firstName", "reddy");
        data.put("lastName", "Hari");
        data.put("email", "hari.reddy@gmail.com");
        data.put("age", 35);
        data.put("yearsOfExperience", 9);
        data.put("salary", 50000);
        data.put("jobTitle", "Web Developer");
        data.put("department", "Product");

        given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .post("http://localhost:8081/empData/create")
                .then()
                .assertThat().statusCode(HttpStatus.CREATED.value())
                .body("firstName", equalTo("reddy"))
                .body("age", equalTo(35))
                .log().all();

    }

    @Test(priority = 3)
    void testPostEmployeeWithId() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("firstName", "reddy");
        data.put("lastName", "Hari");
        data.put("email", "hari.reddy@gmail.com");
        data.put("age", 35);
        data.put("yearsOfExperience", 9);
        data.put("salary", 50000);
        data.put("jobTitle", "Web Developer");
        data.put("department", "Product");

        id = given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .post("http://localhost:8081/empData/create")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                //.body("firstName", equalTo("reddy"))
                //.body("age", equalTo(35))
                .log()
                .all()
                .extract()
                .path("id");
    }

    @Test(dependsOnMethods = "testPostEmployeeWithId")
    void testPutEmployeeWithId() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("firstName", "Bablu");
        data.put("lastName", "Deekshith");

        given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .when()
                .put("http://localhost:8081/empData/"+id)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                //.body("firstName", equalTo("Bablu"))
                //.body("lastName", equalTo("Deekshith"))
                //.body("age", equalTo(35))
                .log()
                .all();
    }

    //@Test(priority = 2,dependsOnMethods = "testPostEmployeeWithId")
    @Test(dependsOnMethods = {"testPostEmployeeWithId","testPutEmployeeWithId"})
    void deleteEmployeeByIdTest() throws JSONException {
        given()
                .when()
                .delete("http://localhost:8081/empData/"+id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
