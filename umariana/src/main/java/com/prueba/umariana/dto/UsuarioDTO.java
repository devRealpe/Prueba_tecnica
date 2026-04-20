package com.prueba.umariana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id; // Cambio: era "idUsuario", ahora "id" para coincidir con la entidad

    private String name; // Cambio: era "nombre", ahora "name" para que el frontend Angular lo lea
                         // directamente

    private String email; // Cambio: era "correo", ahora "email" para coincidir con la entidad y el
                          // frontend
}