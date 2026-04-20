package com.prueba.umariana.services;

import com.prueba.umariana.dto.UsuarioDTO;
import com.prueba.umariana.repository.UsuarioRepository;
import com.prueba.umariana.entity.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    // Mostrar usuarios
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Crear usuario
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getCorreo())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getCorreo());
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }

    // Actualizar usuario
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));
        if (!usuario.getEmail().equals(usuarioDTO.getCorreo()) &&
                usuarioRepository.existsByEmail(usuarioDTO.getCorreo())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getCorreo());
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(updatedUsuario);
    }

    // Eliminar usuario
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail());
    }
}
