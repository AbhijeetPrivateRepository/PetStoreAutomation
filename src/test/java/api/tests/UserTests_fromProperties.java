package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.endpoints.UserEndPoint_fromPropertiesFile;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests_fromProperties {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser(){
		logger.info("*****Creating user*****");
		Response response=UserEndPoint_fromPropertiesFile.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(),200);
		logger.info("*****User is Created*****");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("*****Reading User*****");
		Response response=UserEndPoint_fromPropertiesFile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		logger.info("*****User Displayed*****");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		logger.info("*****Updating user*****");
		Response response=UserEndPoint_fromPropertiesFile.updateUser(userPayload,this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.statusCode(),200);
		logger.info("*****User is updated*****");
		
		//checking data after update
		Response responseAfterUpdate=UserEndPoint.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.statusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("*****Deleteing user*****");
		Response response=UserEndPoint_fromPropertiesFile.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		logger.info("*****User Deleted*****");
	}

}
