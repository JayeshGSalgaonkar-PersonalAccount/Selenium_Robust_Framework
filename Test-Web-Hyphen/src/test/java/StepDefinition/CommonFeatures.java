package StepDefinition;

import DriverFactory.DriverManager;
import io.restassured.RestAssured;

import java.io.IOException;
import java.util.Properties;

public class CommonFeatures {

    static Properties properties = new Properties();

    public static Object firstApiCall() throws IOException {

        properties = DriverManager.propertiesFile();
        return RestAssured.get(properties.getProperty("movie_api"));
    }
}
