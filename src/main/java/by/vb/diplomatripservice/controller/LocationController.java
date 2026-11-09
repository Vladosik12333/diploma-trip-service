package by.vb.diplomatripservice.controller;

import by.vb.diplomatripservice.dto.BasicResponse;
import by.vb.diplomatripservice.constant.LocationConstants;
import by.vb.diplomatripservice.dto.LocationOptionResponse;
import by.vb.diplomatripservice.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Locations", description = "Location search")
public class LocationController {
	private final LocationService locationService;

	@GetMapping("/autocomplete")
	@Operation(summary = "Autocomplete locations", description = "Returns matching city suggestions from Google Places.")
	@ApiResponse(responseCode = "200", description = "Location suggestions returned")
	public BasicResponse<List<LocationOptionResponse>> autocomplete(
			@Parameter(description = "Text to search for", example = "Warsaw")
			@RequestParam @NotBlank @Size(min = LocationConstants.AUTOCOMPLETE_MIN_LENGTH,
					max = LocationConstants.AUTOCOMPLETE_MAX_LENGTH) String userInput
	) {
		log.info("Received location autocomplete request. User input={}", userInput);
		return new BasicResponse<>(locationService.autocomplete(userInput), StringUtils.EMPTY, true);
	}
}
