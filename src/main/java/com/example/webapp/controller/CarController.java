package com.example.webapp.controller;

import com.example.webapp.dto.CarDto;
import com.example.webapp.service.CarUseCases;
import com.example.webapp.service.EngineUseCases;
import com.example.webapp.service.EquipmentOptionUseCases;
import com.example.webapp.view.CarForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarUseCases carUseCases;
    private final EngineUseCases engineUseCases;
    private final EquipmentOptionUseCases equipmentOptionUseCases;

    @GetMapping
    public String carList(Model model) {
        final var cars = carUseCases.getAllCars();
        model.addAttribute("cars", cars);
        return "car-list";
    }

    @GetMapping("/create")
    public String createCarForm(Model model) {
        final var engines = engineUseCases.getAllEngines();
        final var equipmentOptions = equipmentOptionUseCases.getAllEquipmentOptions();
        model.addAttribute("engines", engines);
        model.addAttribute("equipmentOptions", equipmentOptions);
        model.addAttribute("carForm", new CarForm());
        return "car-form";
    }

    @GetMapping("/edit/{id}")
    public String editCarForm(Model model, @PathVariable Long id) {
        final var engines = engineUseCases.getAllEngines();
        final var equipmentOptions = equipmentOptionUseCases.getAllEquipmentOptions();
        final var car = carUseCases.getAllCars().stream().filter(c -> c.id().equals(id)).findFirst().orElseThrow();
        model.addAttribute("engines", engines);
        model.addAttribute("equipmentOptions", equipmentOptions);
        model.addAttribute("carForm", new CarForm(car));
        return "car-form";
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute("carForm") CarForm carForm) {
        final var car = createCarFromCarForm(carForm, null);
        carUseCases.createCar(car);
        return "redirect:/cars";
    }

    @PutMapping("/update/{id}")
    public String updateCar(@PathVariable Long id, @ModelAttribute("carForm") CarForm carForm) {
        final var car = createCarFromCarForm(carForm, id);
        carUseCases.updateCar(id, car);
        return "redirect:/cars";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carUseCases.deleteCar(id);
        return "redirect:/cars";
    }

    private CarDto createCarFromCarForm(CarForm carForm, Long id) {
        final var engine = engineUseCases.getAllEngines().stream().filter(e -> e.id().equals(carForm.getEngineId())).findFirst().orElseThrow();
        final var equipmentOptions = equipmentOptionUseCases.getAllEquipmentOptions().stream().filter(e -> carForm.getEquipmentOptionsIds().contains(e.id())).toList();
        return new CarDto(id, carForm.getName(), carForm.getColor(), engine, equipmentOptions);
    }

}
