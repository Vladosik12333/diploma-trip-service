package by.vb.diplomatripservice.dto;

import java.math.BigDecimal;

public record TripLocationResponse(
	String googlePlaceId,
	String city,
	String country,
	BigDecimal latitude,
	BigDecimal longitude
) {
}
