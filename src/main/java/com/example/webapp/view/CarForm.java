package com.example.webapp.view;

import com.example.webapp.dto.CarDto;
import com.example.webapp.dto.EquipmentOptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarForm {

    private Long id;
    private String name;
    private String color;
    private Long engineId;
    private List<Long> equipmentOptionsIds;

    public CarForm(CarDto car) {
        id = car.id();
        name = car.name();
        color = car.color();
        engineId = car.engine().id();
        equipmentOptionsIds = car.equipmentOptions().stream().map(EquipmentOptionDto::id).toList();
    }

}
