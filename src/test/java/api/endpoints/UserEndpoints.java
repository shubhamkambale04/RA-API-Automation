package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response response;
	
	public static Response createUser(User userData) {
		response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(userData)

				.when().post(Routes.post_url);

		return response;
	}

	public static Response readUser(String username) {
		response = given().pathParam("username", username)

				.when().get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String username, User userData) {
		response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(userData)
				.pathParam("username", username)

				.when().put(Routes.put_url);

		return response;
	}

	public static Response deleteUser(String username) {
		response = given().pathParam("username", username)

				.when().delete(Routes.delete_url);

		return response;
	}
}
