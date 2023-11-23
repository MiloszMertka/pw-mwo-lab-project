package com.example.webapp.controller;

import com.example.webapp.dto.EngineDto;
import com.example.webapp.service.EngineUseCases;
import com.example.webapp.view.EngineForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/engines")
@RequiredArgsConstructor
public class EngineController {

    private final EngineUseCases engineUseCases;

    @GetMapping
    public String engineList(Model model) {
        final var engines = engineUseCases.getAllEngines();
        model.addAttribute("engines", engines);
        return "engine-list";
    }

    @GetMapping("/create")
    public String createEngineForm(Model model) {
        model.addAttribute("engineForm", new EngineForm());
        return "engine-form";
    }

    @GetMapping("/edit/{id}")
    public String editEngineForm(Model model, @PathVariable Long id) {
        final var engine = engineUseCases.getAllEngines().stream().filter(e -> e.id().equals(id)).findFirst().orElseThrow();
        model.addAttribute("engineForm", new EngineForm(engine));
        return "engine-form";
    }

    @PostMapping("/create")
    public String createEngine(@ModelAttribute("engineForm") EngineForm engineForm) {
        final var engine = createEngineFromEngineForm(engineForm, null);
        engineUseCases.createEngine(engine);
        return "redirect:/engines";
    }

    @PutMapping("/update/{id}")
    public String updateEngine(@PathVariable Long id, @ModelAttribute("engineForm") EngineForm engineForm) {
        final var engine = createEngineFromEngineForm(engineForm, id);
        engineUseCases.updateEngine(id, engine);
        return "redirect:/engines";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEngine(@PathVariable Long id) {
        engineUseCases.deleteEngine(id);
        return "redirect:/engines";
    }

    private EngineDto createEngineFromEngineForm(EngineForm engineForm, Long id) {
        return new EngineDto(id, engineForm.getName(), engineForm.getHorsePower());
    }

}
