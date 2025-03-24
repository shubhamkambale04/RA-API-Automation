package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndpoints;
import api.payload.User;
import api.payload.UserDataManager;
import api.utilities.DataProviders;

public class DataDrivenTests extends UserDataManager    {

	@Test(priority = 1, dataProvider = "myDDTData", dataProviderClass = DataProviders.class)
	public void testPostUserDDT(String userid, String username, String firstName, String lastName,
            String email, String password, String phone) 
	{   
		logger.info("Creating user using DataProvider...");
        User userDDData = createUserFromDataProvider(userid, username, firstName, lastName, email, password, phone);
        
		response = UserEndpoints.createUser(userDDData);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserDDT(String username) 
	{
		response = UserEndpoints.deleteUser(username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
