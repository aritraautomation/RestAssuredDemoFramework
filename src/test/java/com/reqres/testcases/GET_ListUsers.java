package com.reqres.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.reqres.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class GET_ListUsers extends BaseTest{
	
	@BeforeMethod
	void listOfUsersSetup(){
		logger.info("********Getting ALL Users Details********");
		
		RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/api/users?page=2");
		jsonpath = response.jsonPath();
	}
	
	@Test
	void getBody(){
		logger.info("******Response Body**********");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body===> " + responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void extractNodesFromBody(){
		logger.info("******Get Response Body**********");
		
		logger.info(jsonpath.get("page"));
		logger.info(jsonpath.get("per_page"));
		logger.info(jsonpath.get("total"));
		logger.info(jsonpath.get("total_pages"));
		logger.info(jsonpath.get("data.id[0]"));
		logger.info(jsonpath.get("data.first_name[0]"));
		
		Assert.assertEquals(jsonpath.get("data.id[0]"), 7);
		Assert.assertEquals(jsonpath.get("data.first_name[0]"), "Michael");	
	}
	
	@Test
	void getStatusCode(){
		logger.info("******Get Response Code**********");
		
		int statusCode = response.getStatusCode();
		logger.info("Response Status Code : " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void getHeader(){
		logger.info("****Response Header Validation*******");
		
		Headers listHeaders = response.headers();
		for(Header header:listHeaders){
			logger.info("Response Headers are : " + header.getName() + " " + header.getValue());
		}
		
		String contentType = response.header("content-type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	

}
