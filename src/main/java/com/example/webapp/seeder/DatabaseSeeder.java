package com.example.webapp.seeder;

import com.example.webapp.repository.CarRepository;
import com.example.webapp.repository.EngineRepository;
import com.example.webapp.repository.EquipmentOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final EngineSeeder engineSeeder;
    private final EquipmentOptionSeeder equipmentOptionSeeder;
    private final CarSeeder carSeeder;
    private final EngineRepository engineRepository;
    private final EquipmentOptionRepository equipmentOptionRepository;
    private final CarRepository carRepository;

    @Override
    public void run(String... args) {
        clearDatabase();
        engineSeeder.seed(10);
        equipmentOptionSeeder.seed(5);
        carSeeder.seed(10);
    }

    private void clearDatabase() {
        carRepository.deleteAll();
        engineRepository.deleteAll();
        equipmentOptionRepository.deleteAll();
    }

}
