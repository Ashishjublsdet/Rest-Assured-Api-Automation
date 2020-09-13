package com.study.automation.TestCases;

import com.study.automation.BaseTest.BaseTest;
import com.study.automation.ResponsePojo.CreateUser;
import com.study.automation.ResponsePojo.Users;
import com.study.automation.StatusCode.HttpStatusCode;
import com.study.automation.constants.Constants;
import com.study.automation.payLoad.PayLoad;
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

        int totalCount = users[0].getTotal();

        //Validate the Json Response
        Assert.assertEquals(totalCount, 12);
    }

    @Test
    public void POST_REQUEST() throws Exception {
        String name = "Ashish";
        String job = "automation";
        Response response = apiHelper.postRequest(Constants.CREATE_USER, PayLoad.createUser(name, job));
        CreateUser[] createUsers = objectMapper.readValue(response.getBody().asString(), CreateUser[].class);

        //Validate status code
        apiHelper.ValidateStatusCode(response, HttpStatusCode.CREATED);

        //Validate data
        Assert.assertEquals(createUsers[0].getName(), name);
    }

    @Test
    public void UPDATE_REQUEST() throws Exception {
        String name = "Ashish";
        String job = "Automation update";
        Response response = apiHelper.postRequest(Constants.UPDATE_USER, PayLoad.createUser(name, job));
        CreateUser[] createUsers = objectMapper.readValue(response.getBody().asString(), CreateUser[].class);

        //Validate status code
        apiHelper.ValidateStatusCode(response, HttpStatusCode.CREATED);

        //Validate data
        Assert.assertEquals(createUsers[0].getJob(), job);
    }

}


