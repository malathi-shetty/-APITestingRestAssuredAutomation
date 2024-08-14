package restapi;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_RequestAuthentication {
	
	@Test
	public void AuthenticationTest()
	{
RestAssured.baseURI="https://demoqa.com/authentication/CheckForAuthentication";

//Basic Authentication
PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
authscheme.setUserName("ToolsQA");
authscheme.setPassword("TestPassword");
RestAssured.authentication=authscheme;
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET,"/");
		
			
				//Print response in console
				String responseBody = response.getBody().asPrettyString();
				System.out.println("Response Body is: " + responseBody);
				
				//status code validation
				int statuscode = response.getStatusCode();
				System.out.println("Status code is: " + statuscode);
				Assert.assertEquals(statuscode, 200);
				
				//status line verification
				String statusLine = response.getStatusLine();
				System.out.println("Status Line is: " + statusLine);
				Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

}
