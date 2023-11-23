package com.example.webapp.view;

import com.example.webapp.dto.EquipmentOptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentOptionForm {

    private Long id;
    private String name;

    public EquipmentOptionForm(EquipmentOptionDto equipmentOption) {
        id = equipmentOption.id();
        name = equipmentOption.name();
    }

}
