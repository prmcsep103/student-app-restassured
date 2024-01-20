package com.studentapp.myfirsttest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay Vaghani
 */
public class MyFirstTest {

    @Test
    public void getAllStudentInfo() {
        /*given()
                .when()
                .get("http://localhost:8081/student/list")
                .then()
                .statusCode(200);*/

        Response response = given()
                .when()
                .get("/list");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
