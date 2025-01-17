package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {

	RequestSpecification rso;
	Response response;
	TestDataBuild data = new TestDataBuild();
	JsonPath js;
	static String placeId;

	@Given("Add Place Payload with {string} {string} and {string}")
	public void add_place_payload(String name, String address, String language) {
		rso = given().spec(requestSpecification()).header("Content-Type", "application/json")
				.body(data.addPlacePayload(name, address, language));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		if (method.equalsIgnoreCase("POST")) {
			response = rso.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = rso.when().get(resourceAPI.getResource());
		}
	}

	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer expectedStatusCode) {
		assertEquals((Integer) response.statusCode(), expectedStatusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		assertEquals(getJsonValue(response, key), expectedValue);
	}

	@Then("Verify place ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) {
		placeId = getJsonValue(response, "place_id");
		rso = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_post_http_request(resourceName, "GET");
		String actualName = getJsonValue(response, "name");
		assertEquals(expectedName, actualName);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() {
		rso = given().spec(requestSpecification()).header("Content-Type", "application/json")
				.body(data.deletePlacePayload(placeId));
	}
}
