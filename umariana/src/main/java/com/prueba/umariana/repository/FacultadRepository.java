package com.prueba.umariana.repository;

import com.prueba.umariana.entity.Facultad;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long> {
    Optional<Facultad> findById(Long id);
}
