package com.study.automation.Utility;

import com.study.automation.constants.GlobalVariable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Utility {
    private static Properties properties;
    public static ObjectMapper objectMapper;


    synchronized public static Properties loadProperty(String path) throws Exception {
        if (properties == null) {
            properties = new Properties();
            properties.load(new FileInputStream(path));
            return properties;
        }
        return properties;
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            return new ObjectMapper().configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        }
        return objectMapper;
    }

}
