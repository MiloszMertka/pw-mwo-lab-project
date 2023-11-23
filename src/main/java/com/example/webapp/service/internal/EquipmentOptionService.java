package com.example.webapp.service.internal;

import com.example.webapp.dto.EquipmentOptionDto;
import com.example.webapp.mapper.EquipmentOptionMapper;
import com.example.webapp.model.EquipmentOption;
import com.example.webapp.repository.EquipmentOptionRepository;
import com.example.webapp.service.EquipmentOptionUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
class EquipmentOptionService implements EquipmentOptionUseCases {

    private static final String EQUIPMENT_OPTION_NOT_FOUND_ERROR_MESSAGE = "Equipment option not found";
    private static final String EQUIPMENT_OPTION_ALREADY_EXISTS_ERROR_MESSAGE = "Equipment option already exists";
    private final EquipmentOptionRepository equipmentOptionRepository;
    private final EquipmentOptionMapper equipmentOptionMapper;

    @Override
    public List<EquipmentOptionDto> getAllEquipmentOptions() {
        final var equipmentOptions = equipmentOptionRepository.findAll();
        return equipmentOptions.stream()
                .map(equipmentOptionMapper::mapEquipmentOptionToEquipmentOptionDto)
                .toList();
    }

    @Override
    public EquipmentOptionDto getEquipmentOption(Long id) {
        final var equipmentOption = getEquipmentOptionById(id);
        return equipmentOptionMapper.mapEquipmentOptionToEquipmentOptionDto(equipmentOption);
    }

    @Override
    public void createEquipmentOption(EquipmentOptionDto equipmentOptionDto) {
        final var equipmentOption = equipmentOptionMapper.mapEquipmentOptionDtoToEquipmentOption(equipmentOptionDto);
        checkIfEquipmentOptionAlreadyExists(equipmentOption);
        equipmentOptionRepository.save(equipmentOption);
    }

    @Override
    public void updateEquipmentOption(Long id, EquipmentOptionDto equipmentOptionDto) {
        final var equipmentOption = getEquipmentOptionById(id);
        equipmentOptionMapper.updateEquipmentOptionFromEquipmentOptionDto(equipmentOption, equipmentOptionDto);
        equipmentOptionRepository.save(equipmentOption);
    }

    @Override
    public void deleteEquipmentOption(Long id) {
        final var equipmentOption = getEquipmentOptionById(id);
        equipmentOptionRepository.delete(equipmentOption);
    }

    private EquipmentOption getEquipmentOptionById(Long id) {
        return equipmentOptionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(EQUIPMENT_OPTION_NOT_FOUND_ERROR_MESSAGE));
    }

    private void checkIfEquipmentOptionAlreadyExists(EquipmentOption equipmentOption) {
        if (equipmentOptionRepository.existsByName(equipmentOption.getName())) {
            throw new IllegalStateException(EQUIPMENT_OPTION_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }

}
