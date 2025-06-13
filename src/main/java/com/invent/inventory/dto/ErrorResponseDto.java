package com.invent.inventory.dto;

public record ErrorResponseDto(
        String message,
        String details) {
    public ErrorResponseDto(String message) {
        this(message, null);
    }
}