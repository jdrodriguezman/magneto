package com.meli.mutant.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@NoArgsConstructor
@Entity(name = "humans")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Integer id;

    @NotNull
    private boolean mutant;

    @NotNull
    private String dna;
}
