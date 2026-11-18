package by.vb.diplomatripservice.dto.geonames;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoNamesSearchResponse(List<GeoNamesCityResponse> geonames, GeoNamesStatusResponse status) {
}
