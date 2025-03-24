package api.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.endpoints.UserEndpoints;
import api.payload.UserDataManager;
import io.restassured.response.Response;

public class UserTests extends UserDataManager {

	@BeforeClass
	public void setUp() {
		logger.info("Setting up UserTests - Generating Random User Data");
		userData = generateRandomUser();
	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("creating user");
		response = UserEndpoints.createUser(userData);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user created");
	}

	@Test(priority = 2)
	public void testGetUser() {
		logger.info("reading user");
		response = UserEndpoints.readUser(userData.getUsername());
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

		response = UserEndpoints.updateUser(userData.getUsername(), userData);
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
		response = UserEndpoints.deleteUser(userData.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user deleted");
	}

	@AfterClass
	public void tearDown() {
		logger.info("Cleaning up UserTests data...");
		userData = null;
	}
}
