package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.constant.GooglePlacesConstants;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.maps.places.v1.AutocompletePlacesRequest;
import com.google.maps.places.v1.AutocompletePlacesResponse;
import com.google.maps.places.v1.GetPlaceRequest;
import com.google.maps.places.v1.Place;
import com.google.maps.places.v1.PlacesClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlacesApiService {
	private final PlacesClient placesClient;

	public AutocompletePlacesResponse autocomplete(String userInput) {
		AutocompletePlacesRequest request = AutocompletePlacesRequest.newBuilder()
				.setInput(userInput)
				.addIncludedPrimaryTypes(GooglePlacesConstants.LOCALITY_TYPE)
				.build();

		AutocompletePlacesResponse response = placesClient.autocompletePlaces(request);
		log.info("Received autocomplete response={}", response);
		return response;
	}

	public Place getPlace(String placeId) {
		log.info("Requesting place details for place {}", placeId);

		GetPlaceRequest request = GetPlaceRequest.newBuilder()
				.setName(GooglePlacesConstants.PLACE_RESOURCE_PREFIX + placeId)
				.build();

		GrpcCallContext callContext = GrpcCallContext.createDefault()
				.withExtraHeaders(Map.of(GooglePlacesConstants.FIELD_MASK_HEADER,
						List.of(GooglePlacesConstants.DETAILS_FIELD_MASK)));

		Place response = placesClient.getPlaceCallable().call(request, callContext);
		log.info("Received place details response={}", response);
		return response;
	}
}
