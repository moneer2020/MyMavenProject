package com.hrms.utils;

import org.json.JSONObject;

public class APIPayloadCommonMethods {

    public static String createEmployeeBody() {

        String createEmployeeBody = "{\n" +
                "  \"emp_firstname\": \"Created\",\n" +
                "  \"emp_lastname\": \"Employee\",\n" +
                "  \"emp_middle_name\": \"First\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2021-02-28\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Manager\"\n" +
                "}";

        return createEmployeeBody;
    }

    public static String createEmployeePayload() {

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Created");
        obj.put("emp_lastname","Employee");
        obj.put("emp_middle_name","First");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","2021-02-28");
        obj.put("emp_status","Employee");
        obj.put("emp_job_title","Manager");
        return obj.toString();
    }

    public static String generateTokenPayload(){
        JSONObject obj = new JSONObject();
        obj.put("email","ofnkcanwegoslower@slow.com");
        obj.put("password","fknslow");
        return obj.toString();
    }


}
