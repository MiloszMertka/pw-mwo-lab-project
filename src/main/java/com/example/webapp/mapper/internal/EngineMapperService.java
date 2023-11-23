package com.example.webapp.mapper.internal;

import com.example.webapp.dto.EngineDto;
import com.example.webapp.mapper.EngineMapper;
import com.example.webapp.model.Engine;
import com.example.webapp.repository.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class EngineMapperService implements EngineMapper {

    private final EngineRepository engineRepository;

    @Override
    public EngineDto mapEngineToEngineDto(Engine engine) {
        return new EngineDto(engine.getId(), engine.getName(), engine.getHorsePower());
    }

    @Override
    public Engine mapEngineDtoToEngine(EngineDto engineDto) {
        return engineDto.id() == null
                ? new Engine(engineDto.name(), engineDto.horsePower())
                : engineRepository.findById(engineDto.id()).orElseThrow();
    }

    @Override
    public void updateEngineFromEngineDto(Engine engine, EngineDto engineDto) {
        engine.setName(engineDto.name());
        engine.setHorsePower(engineDto.horsePower());
    }

}
