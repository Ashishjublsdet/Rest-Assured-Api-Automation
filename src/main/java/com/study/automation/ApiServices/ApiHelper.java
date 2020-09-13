package com.study.automation.ApiServices;

import com.study.automation.StatusCode.HttpStatusCode;
import com.study.automation.customAssertion.CustomAssert;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public ApiHelper(String URI) {
        RestAssured.baseURI = URI;
    }

    public Response getRequest(String params) {

        Response response =
                given().relaxedHTTPSValidation()
                        .with()
                        .contentType(ContentType.JSON)
                        .get(params).then().extract().response();
        return response;
    }

    public static Response postRequest(String URI, String body) {
        Response response =
                given().relaxedHTTPSValidation().log().all()
                        .with()
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when().post(URI)
                        .then().extract().response();
        return response;
    }

    public static Response postRequestWithHeader(String URI, Map<String, String> map, String body) {
        Response response = given()
                .relaxedHTTPSValidation().log().all()
                .with()
                .contentType(ContentType.JSON)
                .headers(map)
                .body(body)
                .when().post(URI);
        return response;
    }

    public static Response PostRequestWithHeader(String URI, Map<String, String> headerMap, Map<String, String> body) {
        Response response = given()
                .relaxedHTTPSValidation()
                .with()
                .contentType(ContentType.JSON)
                .headers(headerMap)
                .body(body)
                .when().post(URI);
        return response;
    }

    public static Response putRequestWithHeader(String URI, Map<String, String> map, String body) {
        Response response = given()
                .relaxedHTTPSValidation().log().all()
                .with()
                .contentType(ContentType.JSON)
                .headers(map)
                .body(body)
                .when().put(URI);
        return response;
    }

    public static void ValidateStatusCode(Response response, HttpStatusCode httpStatusCode) {
        int statusCode = response.then().extract().statusCode();
        CustomAssert.assertEquals(statusCode, httpStatusCode.getCode(), "Verify Http Status Code ::");
    }

}
