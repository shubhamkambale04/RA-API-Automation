package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority = 1, dataProvider = "myDDTData", dataProviderClass = DataProviders.class)
	public void testPostUserDDT(String userid, String username, String firstName, String lastName,
            String email, String password, String phone) 
	{
		User userData=new User();
		userData.setUserId(Integer.parseInt(userid));
		userData.setUsername(username);
		userData.setFirstName(firstName);
		userData.setLastName(lastName);
		userData.setEmail(email);
		userData.setPassword(password);
		userData.setPhone(phone);
		
		Response response = UserEndpoints.createUser(userData);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUseDDT(String username) 
	{
		Response response = UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
