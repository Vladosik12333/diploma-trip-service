package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.dto.CreateTripRequest;
import by.vb.diplomatripservice.dto.PageResponse;
import by.vb.diplomatripservice.dto.TripResponse;
import by.vb.diplomatripservice.dto.TripSummaryResponse;
import by.vb.diplomatripservice.exception.ApiException;
import by.vb.diplomatripservice.entity.Category;
import by.vb.diplomatripservice.entity.Trip;
import by.vb.diplomatripservice.entity.TripLocation;
import by.vb.diplomatripservice.repository.TripRepository;
import by.vb.diplomatripservice.mapper.TripRequestMapper;
import by.vb.diplomatripservice.mapper.TripResponseMapper;
import by.vb.diplomatripservice.mapper.TripSummaryResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {
	private static final Sort USER_TRIPS_SORT = Sort.by(Sort.Order.asc("startDate"),
			Sort.Order.asc("id"));

	private final TripRepository tripRepository;
	private final CategoryService categoryService;
	private final LocationService locationService;
	private final TripRequestMapper tripRequestMapper;
	private final TripResponseMapper tripResponseMapper;
	private final TripSummaryResponseMapper tripSummaryResponseMapper;

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

	@Transactional(readOnly = true)
	public TripResponse getUserTrip(Long tripId, Long userId) {
		Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
				.orElseThrow(() -> {
					log.warn("Trip not found. tripId={}, userId={}", tripId, userId);
					return new ApiException(HttpStatus.NOT_FOUND, "Trip not found");
				});
		log.info("Found trip={}", trip);
		return tripResponseMapper.mapTo(trip);
	}

	@Transactional(readOnly = true)
	public PageResponse<TripSummaryResponse> getUserTrips(Long userId, String search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, USER_TRIPS_SORT);
		Page<Trip> found = StringUtils.isBlank(search)
				? tripRepository.findAllByUserId(userId, pageable)
				: tripRepository.searchUserTrips(userId, toLikePattern(search), pageable);

		log.info("Found {} of {} trips for userId={}, search={}, page={}, size={}",
				found.getNumberOfElements(), found.getTotalElements(), userId, search, page, size);
		return PageResponse.of(found, tripSummaryResponseMapper::mapTo);
	}

	private static String toLikePattern(String search) {
		String escaped = search.trim().toLowerCase(Locale.ROOT)
				.replace("\\", "\\\\")
				.replace("%", "\\%")
				.replace("_", "\\_");
		return "%" + escaped + "%";
	}
}
