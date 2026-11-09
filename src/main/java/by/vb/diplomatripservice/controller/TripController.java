package by.vb.diplomatripservice.controller;

import by.vb.diplomatripservice.dto.BasicResponse;
import by.vb.diplomatripservice.dto.CreateTripRequest;
import by.vb.diplomatripservice.dto.TripResponse;
import by.vb.diplomatripservice.service.TripService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
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
}
