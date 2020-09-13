package com.study.automation.TestCases;

import com.study.automation.BaseTest.BaseTest;
import com.study.automation.constants.Constants;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Case1 extends BaseTest {

    @Test
    public void GET_HTTP_REQUEST() {
        Response response = apiHelper.getRequest(Constants.LIST_USERS);
        System.out.println(response.prettyPrint());
    }
}


