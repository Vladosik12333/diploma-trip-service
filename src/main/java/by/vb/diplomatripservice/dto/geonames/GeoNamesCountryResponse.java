package by.vb.diplomatripservice.dto.geonames;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoNamesCountryResponse(String countryCode, String currencyCode) {
}
