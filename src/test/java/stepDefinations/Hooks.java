package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@deletePlace")
	public void beforeStep() {
		StepDefinations s = new StepDefinations();
		if (StepDefinations.placeId == null) {
			s.add_place_payload("Bhasker", "Borim Ponda", "HN-Hindi");
			s.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			s.verify_place_id_created_maps_to_using("Bhasker", "GetPlaceAPI");
		}
	}

}
