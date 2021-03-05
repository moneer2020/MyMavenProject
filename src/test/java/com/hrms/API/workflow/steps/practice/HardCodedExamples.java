package com.hrms.API.workflow.steps.practice;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExamples {

    /*
     * REST Assured - Java library specifically developed to automate REST endpoints
     *
     * Given - Preparing the request
     * When - What action will you perform, what type of call is being made
     * Then - Verification
     *
     */

    String baseURI = RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQ4MTY0MTcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxNDg1OTYxNywidXNlcklkIjoiMjQyNiJ9.aiJFuTRt7Qe5iuTTq_jH5RXHWjdpvlZmmQR8hro2E5A";
    static String employeeID;

    //    @Test
    public void sampleTest() {
//        BaseURI for all endpoints
//        RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";

//        JWT
//        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQ1NjY5NzYsImlzcyI6ImxvY2FsaG9zdCIsImV4" +
//                "cCI6MTYxNDYxMDE3NiwidXNlcklkIjoiMjQyNiJ9.5mBn8ygk1n-vWg8w-E1PO_G6SQjAO-YRFdgCumBe7EY";

//        Preparing request for /getOneEmployee.php
//        Using .log.all to print out everything being sent with the request
        RequestSpecification preparingGetOneEmployeeRequest = given().
                header("Authorization", token).
                header("Content-Type", "application/json").
                queryParam("employee_id", "13009").log().all();

//        Making call to /getOneEmployee.php
        Response getOneEmployeeResponse = preparingGetOneEmployeeRequest.when().get("/getOneEmployee.php");

//        One way to print a response object
//        System.out.println(getOneEmployeeResponse.asString());

//        Second way to print using rest assured
//        getOneEmployeeResponse.prettyPrint();

//        Using assertThat to verify status code
        getOneEmployeeResponse.then().assertThat().statusCode(200);
    }

    @Test
    public void aPOSTCreateEmployee() {

//        Preparing request for creating an employee
        RequestSpecification createEmployeeRequest = given().
                header("Authorization", token).
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"emp_firstname\": \"Created\",\n" +
                        "  \"emp_lastname\": \"Employee\",\n" +
                        "  \"emp_middle_name\": \"First\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2021-02-28\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"Manager\"\n" +
                        "}");

//        Making a call to /createEmployee.php
        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");

//        Printing response
//        createEmployeeResponse.prettyPrint();

//        Using jsonPath() to view the response body which lets us get the employee ID
//        We are storing the employeeID as a static global variable to use with other calls;
        employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");

//        Optional: Printing employeeID
//        System.out.println(employeeID);

//        Verifying status code is 201
        createEmployeeResponse.then().assertThat().statusCode(201);

//        Verifying response body "Message" is paired with "Entry Created"
//        equalTo() method comes from static Hamcrest package (need to import manually)
//        import static org.hamcrest.Matchers.*
        createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));

//        Verifying created employee first name
        createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("Created"));

//        Verifying server Apache/2.4.39 (Win64) PHP/7.2.18
        createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

    }

    @Test
    public void bGETCreatedEmployee() {

//        Preparing request to get created employee
        RequestSpecification getCreatedEmployeeRequest = given().
                header("Authorization", token).
                header("Content-Type", "application/json").
                queryParam("employee_id", employeeID);

//        Storing response for /getOneEmployee.php
        Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");

//        Printing response
//        getCreatedEmployeeResponse.prettyPrint();

//        Storing response employee ID into empID to compare with static global employee ID
        String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");

//        Comparing empID with stored employeeID from created employee call
        boolean verifyingEmployeeID = empID.equalsIgnoreCase(employeeID);

//        Asserting to verify the above condition is true
        Assert.assertTrue(verifyingEmployeeID);

//        Verifying status code is 200
        getCreatedEmployeeResponse.then().assertThat().statusCode(200);

        String response = getCreatedEmployeeResponse.asString();

        JsonPath js = new JsonPath(response);

        String jsEmployeeID = js.getString("employee[0].employee_id");
        String emp_firstname = js.getString("employee[0].emp_firstname");
        String emp_middle_name = js.getString("employee[0].emp_middle_name");
        String emp_lastname = js.getString("employee[0].emp_lastname");
        String emp_birthday = js.getString("employee[0].emp_birthday");
        String emp_gender = js.getString("employee[0].emp_gender");
        String emp_job_title = js.getString("employee[0].emp_job_title");
        String emp_status = js.getString("employee[0].emp_status");

        /*Asserting response employee ID matches stored employee ID*/
        Assert.assertTrue(employeeID.contentEquals(jsEmployeeID));

        Assert.assertEquals(emp_firstname, "Created");
        Assert.assertEquals(emp_middle_name, "First");
        Assert.assertEquals(emp_lastname, "Employee");
        Assert.assertEquals(emp_birthday, "2021-02-28");
        Assert.assertEquals(emp_gender, "Male");
        Assert.assertEquals(emp_status, "Employee");
        Assert.assertEquals(emp_job_title, "Manager");
    }

    @Test
    public void cGETAllEmployees() {

        RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json").header("Authorization", token);

        Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

//        getAllEmployeesResponse.body().jsonPath().prettyPrint();


        String response = getAllEmployeesResponse.body().asString();
        JsonPath js = new JsonPath(response);

        int count = js.getInt("Employees.size()");

//        for (int i = 0; i < count; i++){
//            String allEmployeeIDs = js.getString("Employees["+ i + "].employee_id");
////            System.out.println(allEmployeeIDs);
//
//            if (allEmployeeIDs.contentEquals(employeeID)){
//                System.out.println("Employee ID: " + employeeID + " is present in the body");
//                String firstNameOfEmpID = js.getString("Employees[" + i + "].emp_firstname");
//                System.out.println(firstNameOfEmpID);
//                break;
//            }
//        }

//        for (int i = 0; i < count; i++){
//            String allEmployeeFirstNames = js.getString("Employees[" + i + "].emp_firstname");
//            System.out.println(allEmployeeFirstNames);
//        }
    }

    @Test
    public void dPUTUpdateCreatedEmployee() {

        /*Preparing request to update created employee*/
        RequestSpecification putUpdateCreatedEmployeeRequest = given().
                header("Application", token)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"employee_id\": \"" + employeeID + "\",\n" +
                        "  \"emp_firstname\": \"Updated\",\n" +
                        "  \"emp_lastname\": \"Created\",\n" +
                        "  \"emp_middle_name\": \"Employee\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2021-02-28\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"Manager\"\n" +
                        "}").log().all();

        /*Storing response for /updateEmployee.php*/
        Response putUpdatedCreatedEmployeeResponse = putUpdateCreatedEmployeeRequest.when().
                put("/updateEmployee.php");

        /*Printing response*/
        putUpdatedCreatedEmployeeResponse.prettyPrint();

    }
}