package com.example.webapp.mapper;

import com.example.webapp.dto.EquipmentOptionDto;
import com.example.webapp.model.EquipmentOption;

public interface EquipmentOptionMapper {

    EquipmentOptionDto mapEquipmentOptionToEquipmentOptionDto(EquipmentOption equipmentOption);

    EquipmentOption mapEquipmentOptionDtoToEquipmentOption(EquipmentOptionDto equipmentOptionDto);

    void updateEquipmentOptionFromEquipmentOptionDto(EquipmentOption equipmentOption, EquipmentOptionDto equipmentOptionDto);

}
