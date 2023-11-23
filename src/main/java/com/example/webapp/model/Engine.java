package com.example.webapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer horsePower;

    public Engine(String name, Integer horsePower) {
        this.name = name;
        this.horsePower = horsePower;
    }

}
