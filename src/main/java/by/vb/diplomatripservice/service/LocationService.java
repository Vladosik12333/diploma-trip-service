package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.constant.GooglePlacesConstants;
import by.vb.diplomatripservice.dto.LocationOptionResponse;
import by.vb.diplomatripservice.exception.ApiException;
import by.vb.diplomatripservice.entity.TripLocation;
import by.vb.diplomatripservice.mapper.LocationOptionResponseMapper;
import com.google.maps.places.v1.AutocompletePlacesResponse;
import com.google.maps.places.v1.Place;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
	private final PlacesApiService placesApiService;
	private final LocationOptionResponseMapper locationOptionResponseMapper;

	public List<LocationOptionResponse> autocomplete(String userInput) {
		AutocompletePlacesResponse response = placesApiService.autocomplete(userInput);

		return response.getSuggestionsList().stream()
				.filter(AutocompletePlacesResponse.Suggestion::hasPlacePrediction)
				.map(AutocompletePlacesResponse.Suggestion::getPlacePrediction)
				.map(locationOptionResponseMapper::mapTo)
				.toList();
	}

	public TripLocation createLocation(String placeId) {
		Place place = placesApiService.getPlace(placeId);

		String city = place.getAddressComponentsList().stream()
				.filter(component -> component.getTypesList().contains(GooglePlacesConstants.LOCALITY_TYPE))
				.map(Place.AddressComponent::getLongText)
				.findFirst().orElse(null);

		String country = place.getAddressComponentsList().stream()
				.filter(component -> component.getTypesList().contains(GooglePlacesConstants.COUNTRY_TYPE))
				.map(Place.AddressComponent::getLongText)
				.findFirst().orElse(null);

		if (city == null || country == null || !place.hasLocation() || place.getId().isBlank()) {
			log.warn("Google Places returned incomplete location data for place {}", place.getId());
			throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Google did not return a complete location for this place");
		}

		TripLocation location = new TripLocation();

		location.setGooglePlaceId(place.getId());
		location.setCity(city);
		location.setCountry(country);
		location.setLatitude(BigDecimal.valueOf(place.getLocation().getLatitude()));
		location.setLongitude(BigDecimal.valueOf(place.getLocation().getLongitude()));

		log.info("Created location from Google Places data. Location={}", location);

		return location;
	}
}
