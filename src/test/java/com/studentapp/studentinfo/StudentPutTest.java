package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class StudentPutTest extends TestBase {

    @Test
    public void updateStudent(){
        String email = TestUtils.getRandomValue() + "primetesting@gmail.com";
        String firstName = TestUtils.getRandomValue() + "Prime";


        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName("Testing");
        studentPojo.setEmail(email);
        studentPojo.setProgramme("Api Automation");
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 102)
                .when()
                .body(studentPojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
