package by.vb.diplomatripservice.dto.geonames;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoNamesCityResponse(
	Long geonameId,
	String name,
	String countryName,
	BigDecimal lat,
	BigDecimal lng,
	GeoNamesStatusResponse status
) {
}
