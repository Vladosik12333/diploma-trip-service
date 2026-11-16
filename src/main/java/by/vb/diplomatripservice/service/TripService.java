package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.dto.CreateTripRequest;
import by.vb.diplomatripservice.dto.PageResponse;
import by.vb.diplomatripservice.dto.TripResponse;
import by.vb.diplomatripservice.dto.TripSummaryResponse;

public interface TripService {
	TripResponse createTrip(CreateTripRequest request);

	TripResponse getUserTrip(Long tripId, Long userId);

	PageResponse<TripSummaryResponse> getUserTrips(Long userId, String search, int page, int size);
}
