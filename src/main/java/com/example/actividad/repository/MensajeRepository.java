package com.example.actividad.repository;

import com.example.actividad.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
    List<Mensaje> findByCreador(String creador);
}
