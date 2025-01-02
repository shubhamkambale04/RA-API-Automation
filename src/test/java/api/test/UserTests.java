package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userData;
	Logger logger;

	@BeforeClass
	public void setUp() {
		faker = new Faker();
		userData = new User();

		userData.setUserId(faker.idNumber().hashCode());
		userData.setUsername(faker.name().username());
		userData.setFirstName(faker.name().firstName());
		userData.setLastName(faker.name().lastName());
		userData.setEmail(faker.internet().safeEmailAddress());
		userData.setPassword(faker.internet().password(5, 10));
		userData.setPhone(faker.phoneNumber().cellPhone());
		userData.setUserStatus(0);
		
		//logs
		logger=LogManager.getLogger(getClass());
	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("creating user");
		Response response = UserEndpoints.createUser(userData);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user created");
	}

	@Test(priority = 2)
	public void testGetUser() {
		logger.info("reading user");
		Response response = UserEndpoints.readUser(userData.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user displayed");
	}

	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("updating user");
		// update data
		userData.setFirstName(faker.name().firstName());
		userData.setLastName(faker.name().lastName());

		Response response = UserEndpoints.updateUser(userData.getUsername(), userData);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user updated");

		// response after user is updated
		Response responseA = UserEndpoints.readUser(userData.getUsername());
		responseA.then().log().all();
		Assert.assertEquals(responseA.getStatusCode(), 200);
		logger.info("updated user displayed");
	}

	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("deleting user");
		Response response = UserEndpoints.deleteUser(userData.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user deleted");
	}
}
