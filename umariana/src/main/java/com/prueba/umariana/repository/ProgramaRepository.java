package com.prueba.umariana.repository;

import com.prueba.umariana.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    Optional<Programa> findById(Long id);
}
