package com.hrms.utils;

import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.ContentType;

import static io.restassured.RestAssured.given;

public class APICommonMethods extends CommonMethods{

    public static RequestSpecification createEmployeeRequest(String token, String createEmployeeBody) {
        return given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(CommonMethods.readJson(createEmployeeBody));
    }

    public static RequestSpecification getOneEmployeeRequest(String token, String employeeID) {
        return given().header("Authorization", token)
                .header("Content-Type", ContentType.APPLICATION_JSON)
                .param("employee_id", employeeID);
    }

    public static RequestSpecification generateTokenRequest(String generateTokenBody){
        return given().header("Content-Type", "application/json")
                .body(CommonMethods.readJson(generateTokenBody));
    }

}
