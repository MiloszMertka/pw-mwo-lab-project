package com.example.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EngineDto(
        Long id,
        @NotBlank(message = "Name cannot be blank") @Size(max = 255, message = "Name cannot exceed 255 characters") String name,
        @NotNull(message = "Horse power must be given") @Positive(message = "Horse power must be positive number") Integer horsePower) {

}