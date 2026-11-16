package by.vb.diplomatripservice.client.places;

import com.google.maps.places.v1.AutocompletePlacesResponse;
import com.google.maps.places.v1.Place;

public interface PlacesApiClient {
	AutocompletePlacesResponse autocomplete(String userInput);

	Place getPlace(String placeId);
}
