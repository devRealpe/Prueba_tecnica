package com.prueba.umariana.controllers;

import com.prueba.umariana.dto.UsuarioDTO;
import com.prueba.umariana.services.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import lombok.RequiredArgsConstructor;

// Voy hacer el crud solo para usuarios, ya no alcanzo teacher

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping
    public UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.createUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    public UsuarioDTO updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.updateUsuario(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}
