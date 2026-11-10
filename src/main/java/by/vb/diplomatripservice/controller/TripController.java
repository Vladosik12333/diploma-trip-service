package by.vb.diplomatripservice.controller;

import by.vb.diplomatripservice.constant.TripConstants;
import by.vb.diplomatripservice.dto.BasicResponse;
import by.vb.diplomatripservice.dto.CreateTripRequest;
import by.vb.diplomatripservice.dto.PageResponse;
import by.vb.diplomatripservice.dto.TripResponse;
import by.vb.diplomatripservice.dto.TripSummaryResponse;
import by.vb.diplomatripservice.service.TripService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/trips")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Trips", description = "Trip management")
public class TripController {
	private final TripService tripService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a trip", description = "Creates a trip with its destination and categories.")
	@ApiResponse(responseCode = "201", description = "Trip created")
	@ApiResponse(responseCode = "422", description = "Destination details are incomplete")
	public BasicResponse<TripResponse> createTrip(@Valid @RequestBody CreateTripRequest request) {
		log.info("Received trip creation request. Request={}", request);
		return new BasicResponse<>(tripService.createTrip(request), StringUtils.EMPTY, true);
	}

	@GetMapping
	@Operation(summary = "List user trips", description = "Returns the user's trips with title, start date, city and country, newest start date first, one page at a time. "
			+ "Optionally filtered by a case-insensitive search over title, city and country.")
	@ApiResponse(responseCode = "200", description = "Trips returned")
	@ApiResponse(responseCode = "400", description = "Invalid userId, search, page or size")
	public BasicResponse<PageResponse<TripSummaryResponse>> getUserTrips(
			@RequestParam @Positive Long userId,
			@Parameter(description = "Text to match in trip title, city or country", example = "Warsaw")
			@RequestParam(required = false) @Size(max = TripConstants.SEARCH_MAX_LENGTH) String search,
			@Parameter(description = "Zero-based page number", example = "0")
			@RequestParam(defaultValue = "0") @Min(0) int page,
			@Parameter(description = "Trips per page", example = "10")
			@RequestParam(defaultValue = "" + TripConstants.DEFAULT_PAGE_SIZE) @Min(1) @Max(TripConstants.MAX_PAGE_SIZE) int size
	) {
		log.info("Received trips list request. userId={}, search={}, page={}, size={}", userId, search, page, size);
		return new BasicResponse<>(tripService.getUserTrips(userId, search, page, size), StringUtils.EMPTY, true);
	}

	@GetMapping("/{tripId}")
	@Operation(summary = "Get trip details", description = "Returns one of the user's trips with its destination, budget and categories.")
	@ApiResponse(responseCode = "200", description = "Trip returned")
	@ApiResponse(responseCode = "400", description = "Invalid tripId or userId")
	@ApiResponse(responseCode = "404", description = "Trip not found for this user")
	public BasicResponse<TripResponse> getUserTrip(
			@Parameter(description = "Trip ID", example = "1") @PathVariable @Positive Long tripId,
			@Parameter(description = "Owner of the trip", example = "1") @RequestParam @Positive Long userId
	) {
		log.info("Received trip details request. tripId={}, userId={}", tripId, userId);
		return new BasicResponse<>(tripService.getUserTrip(tripId, userId), StringUtils.EMPTY, true);
	}
}
