package com.prueba.umariana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaDTO {
    private Long id;

    private String nombre;

    private String descripcion;

    private FacultadDTO facultad;

}
