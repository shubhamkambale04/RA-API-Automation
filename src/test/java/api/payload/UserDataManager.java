package api.payload;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserDataManager {
    protected static Logger logger = LogManager.getLogger(UserDataManager.class);
    protected Response response;
    protected static User userData;
    protected static User userDDData;
    protected static Faker faker;

    // Generate random user data (for UserTests)
    public static User generateRandomUser() {
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
        logger.info("Generated Random User: " + userData.getUsername());
        return userData;
    }

    // Create a user from DataProvider (for DataDrivenTests)
    public static User createUserFromDataProvider(String userid, String username, String firstName, String lastName,
                                                  String email, String password, String phone) {
        userDDData = new User();
        userDDData.setUserId(Integer.parseInt(userid));
        userDDData.setUsername(username);
        userDDData.setFirstName(firstName);
        userDDData.setLastName(lastName);
        userDDData.setEmail(email);
        userDDData.setPassword(password);
        userDDData.setPhone(phone);
        logger.info("Created User from Data Provider: " + userDDData.getUsername());
        return userDDData;
    }
    
    @BeforeMethod
    public void beforeEachTest() throws InterruptedException {
        System.out.println("Waiting before test...");
        Thread.sleep(1000);
    }

    @AfterMethod
    public void afterEachTest() throws InterruptedException {
        System.out.println("Waiting after test...");
        Thread.sleep(1000);
    }
}
