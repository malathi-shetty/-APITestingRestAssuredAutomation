package restapi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {
	
	@Test
	void RegistrationSuccessful()
	{
		
		//Specify Base URI
		RestAssured.baseURI="https://reqres.in/api/users";
	//	RestAssured.baseURI="https://demoqa.com/Account/v1/User";
		//RestAssured.baseURI="http://www.example.net/";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Request payload sending header along with post request
		JSONObject requestParams = new JSONObject();
	//	requestParams.put("first_name", "JohnXYZ");
	//	requestParams.put("last_name", "XYZJohn");
		requestParams.put("username", "JohnXYZ");
		requestParams.put("email", "JohnXYZ@gmail.com");
		requestParams.put("password", "JohnXYZxyx");
		
		
		httpRequest.header("Content-Type","application/json");
		
		//request params will get convert to json & send along with request body
		httpRequest.body(requestParams.toJSONString());
		
		
		//send request - Response Object
		Response response = httpRequest.request(Method.POST,"/register"); // create a new record along with the data send in request params
		
		//print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		
		//status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//Success Code validation
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("Success Code is: " + successCode);
	//	Assert.assertEquals(successCode, "OPERATION_SUCCESS");
	}

}
