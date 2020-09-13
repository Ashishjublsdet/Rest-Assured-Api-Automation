package com.study.automation.customAssertion;

import org.testng.Assert;

public class CustomAssert {

    private CustomAssert() {
    }


    public static void assertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
    }


    public static void assertEquals(int actual, int expected, String message) {
        Assert.assertEquals(actual, expected, message);
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
    }


}
