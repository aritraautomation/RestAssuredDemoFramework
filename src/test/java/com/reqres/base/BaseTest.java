package com.reqres.base;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
//import org.testng.log4testng.Logger;
import org.apache.log4j.Logger;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public static JsonPath jsonpath;
	
	public Logger logger;
	
	@BeforeClass
	public void setup(){
		logger = Logger.getLogger("EmployeesRestAPI");
		PropertyConfigurator.configure("Log4j.properties"); //added logger
		logger.setLevel(Level.DEBUG);
	}

}
