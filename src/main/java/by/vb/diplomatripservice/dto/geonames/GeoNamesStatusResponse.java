package by.vb.diplomatripservice.dto.geonames;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoNamesStatusResponse(Integer value, String message) {
}
