package by.vb.diplomatripservice.dto;

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
	@NotBlank String googlePlaceId,
	@NotEmpty Set<@NotNull @Positive Long> categoryIds
) {
}
