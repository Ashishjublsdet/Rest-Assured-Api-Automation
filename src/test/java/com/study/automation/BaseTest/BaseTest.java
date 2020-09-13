package com.study.automation.BaseTest;

import com.study.automation.ApiServices.ApiHelper;
import com.study.automation.Utility.Utility;
import com.study.automation.constants.GlobalVariable;
import com.study.automation.listners.CustomReporter;
import com.study.automation.customReporter.ExtentManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Map;
import java.util.Properties;

@Listeners({CustomReporter.class})
public class BaseTest {
    public static String BASE_URL;
    Properties properties;
    public ApiHelper apiHelper;


    @BeforeSuite
    public void InitVariables() throws Exception {
        String path = System.getProperty("user.dir") + GlobalVariable.envFilePath + getEnvironment();
        properties = Utility.loadProperty(path);
        this.BASE_URL = properties.getProperty("BASE_URL");
        apiHelper= new ApiHelper(this.BASE_URL);

    }

    @AfterSuite
    public void flush() {
        ExtentManager.addExecutionDetails_ExtentReport();
    }

    public String getEnvironment() {
        GlobalVariable.env = System.getProperty("env");
        if (GlobalVariable.env != null) {
            if (GlobalVariable.env.equalsIgnoreCase("qa")) {
                return "qa.properties";
            } else if (GlobalVariable.env.equalsIgnoreCase("prod")) return "prod.properties";
        }
        return "local.properties";
    }

}
