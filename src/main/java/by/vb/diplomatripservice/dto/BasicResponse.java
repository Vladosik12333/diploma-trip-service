package by.vb.diplomatripservice.dto;


public record BasicResponse<T>(T data, String message, boolean success) {
}