package com.example.webapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CarDto(
        Long id,
        @NotBlank(message = "Name cannot be blank") @Size(max = 255, message = "Name cannot exceed 255 characters") String name,
        @NotBlank(message = "Color cannot be blank") @Size(max = 255, message = "Color cannot exceed 255 characters") String color,
        @NotNull(message = "Engine must be given") @Valid EngineDto engine,
        @NotNull(message = "There must be non empty list of equipment options") List<@NotNull(message = "Equipment option must be given") @Valid EquipmentOptionDto> equipmentOptions
) {

}
