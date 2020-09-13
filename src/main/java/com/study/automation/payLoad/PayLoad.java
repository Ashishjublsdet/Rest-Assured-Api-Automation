package com.study.automation.payLoad;

import com.study.automation.constants.Constants;

public class PayLoad {


    public static String get() {

        String body = "{}";
        return body;

    }

    public static String createUser(String name, String job) {
        String body = "{\n" +
                "\"name\":\"" + name + "\", \n" +
                "\"job\":\"" + job + "\"\n" +
                "}";
        return body;
    }



}

