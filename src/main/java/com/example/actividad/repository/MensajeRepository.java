package com.example.actividad.repository;

import com.example.actividad.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
}
