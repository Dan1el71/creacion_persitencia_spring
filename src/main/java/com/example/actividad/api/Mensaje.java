package com.example.actividad.api;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "mensajes")
@Data
@NoArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creador")
    private Usuario creador;
    private String destinatario;
    @Temporal(TemporalType.DATE)
    private LocalDate created_at;
    private String contenido;
}
