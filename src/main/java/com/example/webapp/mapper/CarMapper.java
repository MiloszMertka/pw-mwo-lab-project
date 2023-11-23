package com.example.webapp.mapper;

import com.example.webapp.dto.CarDto;
import com.example.webapp.model.Car;

public interface CarMapper {

    CarDto mapCarToCarDto(Car car);

    Car mapCarDtoToCar(CarDto carDto);

    void updateCarFromCarDto(Car car, CarDto carDto);

}
