package com.example.webapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String color;

    @ManyToOne(optional = false)
    @EqualsAndHashCode.Exclude
    private Engine engine;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private List<EquipmentOption> equipmentOptions = new ArrayList<>();

    public Car(String name, String color, Engine engine, List<EquipmentOption> equipmentOptions) {
        this.name = name;
        this.color = color;
        this.engine = engine;
        this.equipmentOptions = equipmentOptions;
    }

}
