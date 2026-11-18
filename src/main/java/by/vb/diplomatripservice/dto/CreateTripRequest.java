package by.vb.diplomatripservice.dto;

import by.vb.diplomatripservice.constant.TripConstants;
import by.vb.diplomatripservice.entity.enums.TravelMode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record CreateTripRequest(
	@NotNull @Positive Long userId,
	@NotBlank String title,
	@NotNull LocalDate startDate,
	@NotNull LocalDate endDate,
	@NotNull @PositiveOrZero BigDecimal budgetAmount,
	@NotNull TravelMode travelMode,
	@NotNull @Positive @Max(TripConstants.MAX_TRAVELERS_COUNT) Integer travelersCount,
	@NotNull @Positive Long geonameId,
	@NotEmpty Set<@NotNull @Positive Long> categoryIds
) {
}
