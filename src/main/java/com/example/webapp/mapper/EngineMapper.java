package com.example.webapp.mapper;

import com.example.webapp.dto.EngineDto;
import com.example.webapp.model.Engine;

public interface EngineMapper {

    EngineDto mapEngineToEngineDto(Engine engine);

    Engine mapEngineDtoToEngine(EngineDto engineDto);

    void updateEngineFromEngineDto(Engine engine, EngineDto engineDto);

}
