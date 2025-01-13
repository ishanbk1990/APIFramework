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
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils{

	RequestSpecification rso;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} and {string}")
	public void add_place_payload(String name, String address, String language) {
		try {
			rso = given().spec(requestSpecification()).header("Content-Type", "application/json")
					.body(data.addPlacePayload(name,address,language));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	    
	}
	
	@When("User calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		response = rso.when().post("/maps/api/place/add/json").then().log().all()
				.spec(responseSpecification())
				.extract().response();	   
	}
	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer expectedStatusCode) {
	    assertEquals((Integer)response.statusCode(),expectedStatusCode);	    
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		JsonPath js = new JsonPath(response.asString());
		assertEquals(js.getString(key),expectedValue);
	}

}
