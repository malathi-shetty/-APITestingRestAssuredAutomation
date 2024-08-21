package restapi;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TC_all {

    // Common Base URI
    private static final String BASE_URI = "https://demoqa.com/utilities/weather/city";

    // Set up logging
    @BeforeClass
    public void setupLogging() {
        // Ensure the logs directory exists
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        // Configure RestAssured logging
        RestAssured.config = RestAssured.config()
            .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }

    // Common Request Setup
    private RequestSpecification setupRequest() {
        RestAssured.baseURI = BASE_URI;
        return RestAssured.given();
    }

    private void setupRequestBody(RequestSpecification httpRequest, JSONObject requestParams) {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
    }

    // POST Request
    @Test
    public void postRequest() {
        RequestSpecification httpRequest = setupRequest();
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "JohnXYZ");
        requestParams.put("email", "JohnXYZ@gmail.com");
        requestParams.put("password", "JohnXYZxyx");
        setupRequestBody(httpRequest, requestParams);
        Response response = httpRequest.post("/register");
        Assert.assertEquals(response.getStatusCode(), 201);
        httpRequest.log().all();
    }

    // PUT Request
    @Test
    public void putRequest() {
        RequestSpecification httpRequest = setupRequest();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "JohnUpdated");
        requestParams.put("salary", "60000");
        requestParams.put("age", "30");
        setupRequestBody(httpRequest, requestParams);
        Response response = httpRequest.put("/update/123");
        Assert.assertEquals(response.getStatusCode(), 200);
        httpRequest.log().all();
    }

    // GET Request
    @Test
    public void getRequest() {
        RequestSpecification httpRequest = setupRequest();
        Response response = httpRequest.get("/Mumbai");
        Assert.assertEquals(response.getStatusCode(), 200);
        httpRequest.log().all();
    }

    // PATCH Request
    @Test
    public void patchRequest() {
        RequestSpecification httpRequest = setupRequest();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "JohnPartiallyUpdated");
        setupRequestBody(httpRequest, requestParams);
        Response response = httpRequest.patch("/update/123");
        Assert.assertEquals(response.getStatusCode(), 200);
        httpRequest.log().all();
    }

    // DELETE Request
    @Test
    public void deleteRequest() {
        RequestSpecification httpRequest = setupRequest();
        Response response = httpRequest.delete("/delete/123");
        Assert.assertEquals(response.getStatusCode(), 204);
        httpRequest.log().all();
    }
}

/*
 * Dependencies: Ensure you have the required logging libraries (logback-classic).
Configuration: Add and configure logback.xml.
Logging Setup: Use @BeforeClass to set up logging in RestAssured.
Verification: Confirm logs are being written to logs/rest-assured.log.
 * *

 */