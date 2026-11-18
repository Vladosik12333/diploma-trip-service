package by.vb.diplomatripservice.dto;

import java.math.BigDecimal;

public record LocationResponse(Long geonameId, String city, String country, BigDecimal latitude,
		BigDecimal longitude) {
}
