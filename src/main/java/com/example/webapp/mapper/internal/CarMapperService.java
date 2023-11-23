package com.example.webapp.mapper.internal;

import com.example.webapp.dto.CarDto;
import com.example.webapp.mapper.CarMapper;
import com.example.webapp.mapper.EngineMapper;
import com.example.webapp.mapper.EquipmentOptionMapper;
import com.example.webapp.model.Car;
import com.example.webapp.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CarMapperService implements CarMapper {

    private final EngineMapper engineMapper;
    private final EquipmentOptionMapper equipmentOptionMapper;
    private final CarRepository carRepository;

    @Override
    public CarDto mapCarToCarDto(Car car) {
        final var engineDto = engineMapper.mapEngineToEngineDto(car.getEngine());
        final var equipmentOptionDtos = car.getEquipmentOptions().stream()
                .map(equipmentOptionMapper::mapEquipmentOptionToEquipmentOptionDto)
                .toList();
        return new CarDto(car.getId(), car.getName(), car.getColor(), engineDto, equipmentOptionDtos);
    }

    @Override
    public Car mapCarDtoToCar(CarDto carDto) {
        if (carDto.id() != null) {
            return carRepository.findById(carDto.id()).orElseThrow();
        }

        final var engine = engineMapper.mapEngineDtoToEngine(carDto.engine());
        final var equipmentOptions = carDto.equipmentOptions().stream()
                .map(equipmentOptionMapper::mapEquipmentOptionDtoToEquipmentOption)
                .toList();
        return new Car(carDto.name(), carDto.color(), engine, equipmentOptions);
    }

    @Override
    public void updateCarFromCarDto(Car car, CarDto carDto) {
        car.setName(carDto.name());
        car.setColor(carDto.color());
        car.setEngine(engineMapper.mapEngineDtoToEngine(carDto.engine()));
        synchronizeEquipmentOptions(car, carDto);
    }

    private void synchronizeEquipmentOptions(Car car, CarDto carDto) {
        final var equipmentOptions = carDto.equipmentOptions().stream()
                .map(equipmentOptionMapper::mapEquipmentOptionDtoToEquipmentOption)
                .toList();
        car.getEquipmentOptions().clear();
        car.getEquipmentOptions().addAll(equipmentOptions);
    }

}
