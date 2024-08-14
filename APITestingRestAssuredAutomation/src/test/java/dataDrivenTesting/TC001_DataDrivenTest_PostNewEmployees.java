package dataDrivenTesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_DataDrivenTest_PostNewEmployees {
	@Test
	void postNewEmployees()
	{
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//Here we created Data which can send along with post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", "SMITHXYZ");
		requestParams.put("salary", "70000");
		requestParams.put("age", "40");
		
		//Add a header stating he request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		
		//Add the JSON to the body of the Request
		httpRequest.body(requestParams.toJSONString());
		
		//Send POST REQUEST
		Response response = httpRequest.request(Method.POST, "/create");
		
		//capture response body to perform validations
		
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response Body: " +  responseBody);
		
		Assert.assertEquals(responseBody.contains("SMITHXYZ"),  true);
		Assert.assertEquals(responseBody.contains("70000"),  true);
		Assert.assertEquals(responseBody.contains("40"),  true);
		
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("Status Code is: " + statusCode);
		
		
		
	}
}
