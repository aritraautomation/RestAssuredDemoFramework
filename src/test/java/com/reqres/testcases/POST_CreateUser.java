package com.reqres.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.reqres.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class POST_CreateUser extends BaseTest{
	
	@BeforeMethod
	void createUserSetup(){
		logger.info("********Create User********");
		
		RestAssured.baseURI="https://reqres.in";
		httpRequest = RestAssured.given();
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("name", "morpheus");
		jsonParams.put("job", "leader");
		
		httpRequest.header("Content-type","application/json");
		httpRequest.body(jsonParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/api/users");
		jsonpath = response.jsonPath();
	}
	
	@Test
	void getBody(){
		logger.info("******POST Response Body**********");
		
		String responseBody = response.getBody().asString();
		logger.info("Reponse body : " + responseBody);
		Assert.assertTrue(responseBody!=null);
		
		logger.info("Name of created User is : " + jsonpath.get("name"));
		logger.info("Job Name is : " + jsonpath.get("job"));
		logger.info("Job Id is : " + jsonpath.get("id"));
		logger.info("User created at : " + jsonpath.get("createdAt"));
		
		Assert.assertEquals(jsonpath.get("name"), "morpheus");
		Assert.assertEquals(jsonpath.get("job"), "leader");				
	}
	
	@Test
	void getStatusCode(){
		logger.info("******POST Response Code**********");
		
		int statusCode = response.getStatusCode();
		logger.info("Response Status Code : " + statusCode);
		Assert.assertEquals(statusCode, 201);
	}
	

}
