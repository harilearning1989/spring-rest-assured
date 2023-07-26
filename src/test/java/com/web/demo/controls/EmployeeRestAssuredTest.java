package com.web.demo.controls;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EmployeeRestAssuredTest {

    int id;

    //@Test(priority = 1)
    void testRequestBodyWithHashMap() {
        Map map = new HashMap();
        map.put("empId", 80017);
        map.put("empName", "Kandi Mounika");
        map.put("fatherName", "Kandi Prabhakar Reddy");
        map.put("gender", "Female");
        map.put("mobile", 984820909);
        map.put("category", "OC");
        map.put("manager_id", 98765);
        map.put("salary", 57087);
        map.put("designation", "Architect");
        String values[] = {"A", "B"};
        map.put("letters", values);

        id = given()
                .contentType("application/json")
                .body(map)
                .when()
                .post("http://localhost:8081/emp/create")
                .then()
                .statusCode(201)
                .body("empId", equalTo(80017))
                .body("empName", equalTo("Kandi Mounika"))
                .header("Content-Type", "application/json")
                .log()
                .all()
                .extract().path("id");
    }

    @Test(priority = 1)
    void testRequestBodyWithJsonObject() throws JSONException {
        JSONObject map = new JSONObject();
        map.put("empId", 80017);
        map.put("empName", "Kandi Mounika");
        map.put("fatherName", "Kandi Prabhakar Reddy");
        map.put("gender", "Female");
        map.put("mobile", 984820909);
        map.put("category", "OC");
        map.put("manager_id", 98765);
        map.put("salary", 57087);
        map.put("designation", "Architect");
        String values[] = {"A", "B"};
        map.put("letters", values);

        id = given()
                .contentType("application/json")
                .body(map.toString())
                .when()
                .post("http://localhost:8081/emp/create")
                .then()
                .statusCode(201)
                .body("empId", equalTo(80017))
                .body("empName", equalTo("Kandi Mounika"))
                .header("Content-Type", "application/json")
                .log()
                .all()
                .extract().path("id");
    }

    @Test(priority = 2, dependsOnMethods = {"testRequestBodyWithJsonObject"})
    void deleteEmployee() {
        given()
                .when()
                .delete("http://localhost:8081/emp/" + id)
                .then()
                .statusCode(200);
    }
}
