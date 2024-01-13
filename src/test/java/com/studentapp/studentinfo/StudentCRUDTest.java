package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay Vaghani
 */
public class StudentCRUDTest extends TestBase {
    static String firstName = TestUtils.getRandomValue() + "PrimeUser";
    static String lastName = TestUtils.getRandomValue() + "PrimeUser";
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";

    static int studentId;


    @Test
    public void test001() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(studentPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

    }

    @Test
    public void test002() {
        String s1 = "findAll{it.firstName == '";
        String s2 = "'}.get(0)";

        ValidatableResponse response =
         given()
                .when()
                .get("/list")
                .then().statusCode(200);
        HashMap<String, Object> studentMap = response.extract()
                .path(s1 + firstName + s2);
        response.body(s1 + firstName + s2, hasValue(firstName));
        studentId = (int) studentMap.get("id");
    }

    @Test
    public void test003() {

    }

    @Test
    public void test004() {
        given()
                .pathParam("id", studentId)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);

        given()
                .pathParam("id",studentId)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }

}
