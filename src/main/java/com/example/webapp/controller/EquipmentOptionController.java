package com.example.webapp.controller;

import com.example.webapp.dto.EquipmentOptionDto;
import com.example.webapp.service.EquipmentOptionUseCases;
import com.example.webapp.view.EquipmentOptionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipment-options")
@RequiredArgsConstructor
public class EquipmentOptionController {

    private final EquipmentOptionUseCases equipmentOptionUseCases;

    @GetMapping
    public String equipmentOptionList(Model model) {
        final var equipmentOptions = equipmentOptionUseCases.getAllEquipmentOptions();
        model.addAttribute("equipmentOptions", equipmentOptions);
        return "equipment-option-list";
    }

    @GetMapping("/create")
    public String createEquipmentOptionForm(Model model) {
        model.addAttribute("equipmentOptionForm", new EquipmentOptionForm());
        return "equipment-option-form";
    }

    @GetMapping("/edit/{id}")
    public String editEquipmentOptionForm(Model model, @PathVariable Long id) {
        final var equipmentOption = equipmentOptionUseCases.getAllEquipmentOptions().stream().filter(e -> e.id().equals(id)).findFirst().orElseThrow();
        model.addAttribute("equipmentOptionForm", new EquipmentOptionForm(equipmentOption));
        return "equipment-option-form";
    }

    @PostMapping("/create")
    public String createEquipmentOption(@ModelAttribute("equipmentOptionForm") EquipmentOptionForm equipmentOptionForm) {
        final var equipmentOption = createEquipmentOptionFromEquipmentOptionForm(equipmentOptionForm, null);
        equipmentOptionUseCases.createEquipmentOption(equipmentOption);
        return "redirect:/equipment-options";
    }

    @PutMapping("/update/{id}")
    public String updateEquipmentOption(@PathVariable Long id, @ModelAttribute("equipmentOptionForm") EquipmentOptionForm equipmentOptionForm) {
        final var equipmentOption = createEquipmentOptionFromEquipmentOptionForm(equipmentOptionForm, id);
        equipmentOptionUseCases.updateEquipmentOption(id, equipmentOption);
        return "redirect:/equipment-options";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEquipmentOption(@PathVariable Long id) {
        equipmentOptionUseCases.deleteEquipmentOption(id);
        return "redirect:/equipment-options";
    }

    private EquipmentOptionDto createEquipmentOptionFromEquipmentOptionForm(EquipmentOptionForm equipmentOptionForm, Long id) {
        return new EquipmentOptionDto(id, equipmentOptionForm.getName());
    }

}
