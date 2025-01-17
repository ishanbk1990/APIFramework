package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String address, String lanuage) {
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(lanuage);
		p.setLocation(location);
		p.setName(name);
		p.setWebsite("http://google.com");
		List<String > l = new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		p.setTypes(l);
		return p;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
	}
}
