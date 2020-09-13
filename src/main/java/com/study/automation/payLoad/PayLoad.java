package com.study.automation.payLoad;

import com.study.automation.constants.Constants;

public class PayLoad {


    public static String get() {

        String body = "{}";
        return body;

    }

    public static String getLogin(String mobile, String password) {
        String body = "{\n" +
                "\"mobile\":\"" + mobile + "\", \n" +
                "\"password\":\"" + password + "\"\n" +
                "}";
        return body;
    }



}

