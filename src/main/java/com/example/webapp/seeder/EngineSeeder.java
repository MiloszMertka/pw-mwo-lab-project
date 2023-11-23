package com.example.webapp.seeder;

import com.example.webapp.model.Engine;
import com.example.webapp.repository.EngineRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EngineSeeder implements Seeder {

    private final EngineRepository engineRepository;
    private final Faker faker;

    @Override
    public void seed(int objectsToSeed) {
        final Set<Engine> engines = new HashSet<>();
        for (var i = 0; i < objectsToSeed; i++) {
            final var engine = createEngine();
            if (engines.contains(engine)) {
                i--;
                continue;
            }
            engineRepository.save(engine);
            engines.add(engine);
        }
    }

    private Engine createEngine() {
        return new Engine(
                String.join(" ", faker.lorem().words(3)),
                faker.number().numberBetween(60, 300)
        );
    }

}
