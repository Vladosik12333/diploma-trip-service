package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.dto.CreateTripRequest;
import by.vb.diplomatripservice.dto.TripResponse;
import by.vb.diplomatripservice.exception.ApiException;
import by.vb.diplomatripservice.entity.Category;
import by.vb.diplomatripservice.entity.Trip;
import by.vb.diplomatripservice.entity.TripLocation;
import by.vb.diplomatripservice.repository.TripRepository;
import by.vb.diplomatripservice.mapper.TripRequestMapper;
import by.vb.diplomatripservice.mapper.TripResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {
	private final TripRepository tripRepository;
	private final CategoryService categoryService;
	private final LocationService locationService;
	private final TripRequestMapper tripRequestMapper;
	private final TripResponseMapper tripResponseMapper;

	@Transactional
	public TripResponse createTrip(CreateTripRequest request) {
		if (request.endDate().isBefore(request.startDate())) {
			log.warn("Trip end date {} is before start date {}", request.endDate(), request.startDate());
			throw new ApiException(HttpStatus.BAD_REQUEST, "endDate must not be before startDate");
		}

		List<Category> categories = categoryService.getCategoriesByIds(request.categoryIds());
		if (categories.size() != request.categoryIds().size()) {
			log.warn("Invalid category IDs supplied for trip: {}", request.categoryIds());
			throw new ApiException(HttpStatus.BAD_REQUEST, "One or more category IDs are invalid");
		}

		TripLocation location = locationService.createLocation(request.googlePlaceId());

		Trip trip = tripRequestMapper.mapTo(request);
		trip.setCategories(Set.copyOf(categories));
		trip.setLocation(location);
		location.setTrip(trip);

		Trip saved = tripRepository.save(trip);

		log.info("Created trip={}", saved);

		return tripResponseMapper.mapTo(saved);
	}
}
