package com.example.webapp.repository;

import com.example.webapp.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Long> {

    boolean existsByName(String name);

}
