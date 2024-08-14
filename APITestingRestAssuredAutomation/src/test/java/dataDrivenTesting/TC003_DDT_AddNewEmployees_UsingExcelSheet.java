package dataDrivenTesting;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_DDT_AddNewEmployees_UsingExcelSheet {
	
	
		@Test(dataProvider="empDataProvider")
		void postNewEmployees(String eName, String eSalary, String eAge)
		{
			RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
			
			RequestSpecification httpRequest = RestAssured.given();
			
			//Here we created Data which can send along with post request
			JSONObject requestParams = new JSONObject();
			
			requestParams.put("name", eName);
			requestParams.put("salary", eSalary);
			requestParams.put("age", eAge);
			
			//Add a header stating he request body is a JSON
			httpRequest.header("Content-Type", "application/json");
			
			//Add the JSON to the body of the Request
			httpRequest.body(requestParams.toJSONString());
			
			//Send POST REQUEST
			Response response = httpRequest.request(Method.POST, "/create");
			
			//capture response body to perform validations
			
			String responseBody = response.getBody().asPrettyString();
			System.out.println("Response Body: " +  responseBody);
			
		/*	Assert.assertEquals(responseBody.contains(eName),true);
			Assert.assertEquals(responseBody.contains(eSalary),true);
			Assert.assertEquals(responseBody.contains(eAge),true);
		*/	
			
			int statusCode = response.getStatusCode();
		//	Assert.assertEquals(statusCode, 200);
			System.out.println("Status Code is: " + statusCode);

		}

		
	
		
		@DataProvider(name = "empDataProvider")
		String [][] getEmpData() throws IOException
		
		{
			//Read data from Excel
			String path=System.getProperty("user.dir") + "/src/test/java/dataDrivenTesting/empdata.xlsx";
			int rownum = ExcelUtils.getRowCount(path, "Sheet1");
			int colcount = ExcelUtils.getCellCount(path, "Sheet1", 1);
			
			String empdata[][] = new String[rownum][colcount];
			
			for(int i = 1; i <= rownum; i++) // represent row
			{
				for(int j = 0; j < colcount; j++) // represent column
				{
					empdata[i - 1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
				}
			}
			
			
		//	String empdata[][] = {{"abc123","30000","40"}, {"xyz123","40000","30"} , {"dsd123","70000","50"}};//2 dimensional array
			return(empdata);
			
		}
}
