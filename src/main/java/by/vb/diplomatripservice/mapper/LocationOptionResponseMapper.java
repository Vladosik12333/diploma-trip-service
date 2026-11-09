package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.LocationOptionResponse;
import com.google.maps.places.v1.AutocompletePlacesResponse.Suggestion.PlacePrediction;
import org.springframework.stereotype.Component;

@Component
public class LocationOptionResponseMapper implements Mapper<PlacePrediction, LocationOptionResponse> {
	@Override
	public LocationOptionResponse mapTo(PlacePrediction prediction) {
		return new LocationOptionResponse(prediction.getText().getText(), prediction.getPlaceId());
	}
}
