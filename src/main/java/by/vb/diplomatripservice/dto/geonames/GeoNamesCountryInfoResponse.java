package by.vb.diplomatripservice.dto.geonames;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoNamesCountryInfoResponse(List<GeoNamesCountryResponse> geonames, GeoNamesStatusResponse status) {
}
