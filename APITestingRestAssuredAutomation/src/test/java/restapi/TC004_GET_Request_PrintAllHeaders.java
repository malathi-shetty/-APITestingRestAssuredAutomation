package restapi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_Request_PrintAllHeaders {

	
	@Test
	public void googleMapTest()
	{
		//specify base URI
		RestAssured.baseURI="https://wego.here.com";
		//Request object created
		RequestSpecification httprequest = RestAssured.given();
		
		//Response object created
		Response response = httprequest.request(Method.GET, "/place/nearbysearch&type=supermarket&key=vB0AlAPka8wHRj9E6hvJ?map=18.94018,72.83484,16");
		
		//Print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		
		Headers allheaders = response.headers()	;//capture all the headers from response
		
		for(Header header:allheaders)
		{
			System.out.println(header.getName() + " : " + header.getValue());
		}
}

}