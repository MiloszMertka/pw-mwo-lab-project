package com.example.webapp.service;

import com.example.webapp.dto.CarDto;

import java.util.List;

public interface CarUseCases {

    List<CarDto> getAllCars();

    CarDto getCar(Long id);

    void createCar(CarDto carDto);

    void updateCar(Long id, CarDto carDto);

    void deleteCar(Long id);

}
