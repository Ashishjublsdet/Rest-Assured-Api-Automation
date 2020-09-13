package com.study.automation.TestCases;

import com.study.automation.BaseTest.BaseTest;
import com.study.automation.ResponsePojo.Users;
import com.study.automation.StatusCode.HttpStatusCode;
import com.study.automation.Utility.Utility;
import com.study.automation.constants.Constants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Case1 extends BaseTest {

    @Test
    public void GET_HTTP_REQUEST() throws Exception {
        Response response = apiHelper.getRequest(Constants.LIST_USERS);
        //Validate the status Code
        apiHelper.ValidateStatusCode(response, HttpStatusCode.OK);

        //Deserialize in to POJO
        Users[] users = objectMapper.readValue(response.getBody().asString(), Users[].class);
        users[0].getData().stream().forEach(p ->
                System.out.println(p.getFirst_name()));

        //Validate the Json Response
        Assert.assertEquals();
    }
}


