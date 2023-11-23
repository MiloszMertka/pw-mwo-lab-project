package com.example.webapp.service;

import com.example.webapp.dto.EquipmentOptionDto;

import java.util.List;

public interface EquipmentOptionUseCases {

    List<EquipmentOptionDto> getAllEquipmentOptions();

    EquipmentOptionDto getEquipmentOption(Long id);

    void createEquipmentOption(EquipmentOptionDto equipmentOptionDto);

    void updateEquipmentOption(Long id, EquipmentOptionDto equipmentOptionDto);

    void deleteEquipmentOption(Long id);

}
