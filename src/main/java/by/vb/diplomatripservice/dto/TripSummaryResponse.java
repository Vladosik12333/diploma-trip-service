package by.vb.diplomatripservice.dto;

import java.time.LocalDate;

public record TripSummaryResponse(
	Long id,
	String title,
	LocalDate startDate,
	LocalDate endDate,
	String city,
	String country
) {
}
