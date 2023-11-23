package com.example.webapp.view;

import com.example.webapp.dto.EngineDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineForm {

    private Long id;
    private String name;
    private Integer horsePower;

    public EngineForm(EngineDto engine) {
        id = engine.id();
        name = engine.name();
        horsePower = engine.horsePower();
    }

}
