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
@Table(name = "facultades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facultad {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idFacultad;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;
}
