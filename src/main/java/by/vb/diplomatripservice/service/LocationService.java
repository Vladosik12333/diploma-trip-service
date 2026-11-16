package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.dto.LocationOptionResponse;
import by.vb.diplomatripservice.entity.TripLocation;

import java.util.List;

public interface LocationService {
	List<LocationOptionResponse> autocomplete(String userInput);

	TripLocation createLocation(String placeId);
}
