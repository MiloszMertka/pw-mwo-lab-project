package com.example.webapp.service.internal;

import com.example.webapp.dto.EngineDto;
import com.example.webapp.mapper.EngineMapper;
import com.example.webapp.model.Engine;
import com.example.webapp.repository.EngineRepository;
import com.example.webapp.service.EngineUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
class EngineService implements EngineUseCases {

    private static final String ENGINE_NOT_FOUND_ERROR_MESSAGE = "Engine not found";
    private static final String ENGINE_ALREADY_EXISTS_ERROR_MESSAGE = "Engine already exists";
    private final EngineRepository engineRepository;
    private final EngineMapper engineMapper;

    @Override
    public List<EngineDto> getAllEngines() {
        final var engines = engineRepository.findAll();
        return engines.stream()
                .map(engineMapper::mapEngineToEngineDto)
                .toList();
    }

    @Override
    public EngineDto getEngine(Long id) {
        final var engine = getEngineById(id);
        return engineMapper.mapEngineToEngineDto(engine);
    }

    @Override
    public void createEngine(EngineDto engineDto) {
        final var engine = engineMapper.mapEngineDtoToEngine(engineDto);
        checkIfEngineAlreadyExists(engine);
        engineRepository.save(engine);
    }

    @Override
    public void updateEngine(Long id, EngineDto engineDto) {
        final var engine = getEngineById(id);
        engineMapper.updateEngineFromEngineDto(engine, engineDto);
        engineRepository.save(engine);
    }

    @Override
    public void deleteEngine(Long id) {
        final var engine = getEngineById(id);
        engineRepository.delete(engine);
    }

    private Engine getEngineById(Long id) {
        return engineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ENGINE_NOT_FOUND_ERROR_MESSAGE));
    }

    private void checkIfEngineAlreadyExists(Engine engine) {
        if (engineRepository.existsByName(engine.getName())) {
            throw new IllegalStateException(ENGINE_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }

}
