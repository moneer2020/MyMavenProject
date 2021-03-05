package com.hrms.API.workflow.steps;

import com.hrms.utils.APICommonMethods;
import com.hrms.utils.APIConstants;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateTokenSteps extends APICommonMethods {

    //    String baseURI = RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";
    static String token;

    @Given("a JWT is generated")
    public void a_JWT_is_generated() {

//        /*Preparing request to generate token*/
        RequestSpecification generateTokenRequest = generateTokenRequest(APIConstants.GENERATE_TOKEN_JSON);

        /*Storing response into generateTokenResponse*/
        Response generateTokenResponse = generateTokenRequest.when().post(APIConstants.GENERATE_TOKEN_URI);

        /*Optional: printing response*/
        generateTokenResponse.prettyPrint();

        token = "Bearer " + generateTokenResponse.jsonPath().getString("token");


    }
}
