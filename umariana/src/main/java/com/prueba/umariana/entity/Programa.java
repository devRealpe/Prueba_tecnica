package com.prueba.umariana.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "programas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Programa {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idPrograma;

    @Column(nullable = false)
    private String nombrePrograma;

    @Column(nullable = false)
    private String descripcion;
}
