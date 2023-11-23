package com.example.webapp.seeder;

import com.example.webapp.model.EquipmentOption;
import com.example.webapp.repository.EquipmentOptionRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EquipmentOptionSeeder implements Seeder {

    private final EquipmentOptionRepository equipmentOptionRepository;
    private final Faker faker;

    @Override
    public void seed(int objectsToSeed) {
        final Set<EquipmentOption> equipmentOptions = new HashSet<>();
        for (var i = 0; i < objectsToSeed; i++) {
            final var equipmentOption = createEquipmentOption();
            if (equipmentOptions.contains(equipmentOption)) {
                i--;
                continue;
            }
            equipmentOptionRepository.save(equipmentOption);
            equipmentOptions.add(equipmentOption);
        }
    }

    private EquipmentOption createEquipmentOption() {
        return new EquipmentOption(
                String.join(" ", faker.lorem().words(3))
        );
    }

}
