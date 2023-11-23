package com.example.webapp.repository;

import com.example.webapp.model.EquipmentOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentOptionRepository extends JpaRepository<EquipmentOption, Long> {

    boolean existsByName(String name);

}
