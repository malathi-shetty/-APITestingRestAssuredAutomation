package restapi;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {
	
	//Validating Headers
	
	@Test
	void googleMapTest()
	{
		//specify base URI
		//RestAssured.baseURI="https://maps.googleapis.com/";
	//	RestAssured.baseURI="http://api.weatherapi.com/v1/current.json?key=e8d15e1ffff44074b2532700242906&q=mumbai&aqi=no";
	//RestAssured.baseURI="https://geocode.search.hereapi.com/v1/geocode?q=5+Rue+Daunou%2C+75000+Paris%2C+France";
		RestAssured.baseURI="https://wego.here.com";
		//Request object created
		RequestSpecification httprequest = RestAssured.given();
		
		//Response object created
		Response response = httprequest.request(Method.GET, "/place/nearbysearch&type=supermarket&key=vB0AlAPka8wHRj9E6hvJ?map=18.94018,72.83484,16");
		
		//Print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		
		//status code validation
		int statuscode = response.getStatusCode();
		System.out.println("Status code is: " + statuscode);
	//	Assert.assertEquals(statuscode, 200);
		
		//status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status Line is: " + statusLine);
	//	Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		//Capture details of headers from response - Validating Headers
		String contentType = response.header("Content-Type"); //capture details of contentType header
		System.out.println("Content-Type is: " + contentType);
		Assert.assertEquals(contentType, "text/html");
		
		String server = response.header("Server"); //capture details of contentType header
		System.out.println("Server: " + server);
		Assert.assertEquals(server, "AmazonS3");
		
		
	}

}
