package restapi;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_ExtractValuesOfEachNodeInJSON {
	
	
	@Test
	public void GetWeatherDetails()
	{
		RestAssured.baseURI="https://demoqa.com/utilities/weather/city/";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET,"/Bangalore");
		
		JsonPath jsonpath = response.jsonPath();//response.jsonPath() will capture entire json & store
		// First get the JsonPath object instance from the Response interface
		
		
		
		System.out.println(jsonpath.get("City"));
		System.out.println(jsonpath.get("Temperature"));
		System.out.println(jsonpath.get("Humidity"));
		System.out.println(jsonpath.get("\"Weather Description\""));
		System.out.println(jsonpath.get("\"Wind Speed\""));
		System.out.println(jsonpath.get("\"Wind Direction degree\""));
		
		
	//	Assert.assertEquals(jsonpath.get("Temperature"), "149 Degree celsius"); // temperature may vary so this wont pass every time
		
		
		
	}

}
