package by.vb.diplomatripservice.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record TripResponse(
	Long id,
	String title,
	LocalDate startDate,
	LocalDate endDate,
	BigDecimal budgetAmount,
	String currency,
	TripLocationResponse location,
	List<CategoryResponse> categories,
	Instant createdAt,
	Instant updatedAt
) {
}
