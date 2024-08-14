package restapi;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_Request_ValidatingJsonResponse {
	
	@Test
	void getweatherDetails()
	{
		//specify base URI
		RestAssured.baseURI="https://demoqa.com/utilities/weather/city";
	//	RestAssured.baseURI="http://api.weatherapi.com/v1/current.json?key=e8d15e1ffff44074b2532700242906&q=mumbai&aqi=no";
		
		
		//Request object created
		RequestSpecification httprequest = RestAssured.given();
		
		//Response object created
		Response response = httprequest.request(Method.GET, "/Delhi");
		
		//Print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		
		Assert.assertEquals(responseBody.contains("Delhi"), true);
	//	Assert.assertEquals(responseBody.contains("Dl"), true); // negative scenario
}
}
