package com.example.actividad.api;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String email;
    private String nombre;
    private String apellidos;
    private String edad;
    private String password;
    private String rep_password;
    private Boolean enabled;
    private String foto;
    private String rol;
    @Temporal(TemporalType.DATE)
    private LocalDate created_at;

    @ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name="partidas_usuarios",
            joinColumns= @JoinColumn( name="id_usuario",
                    referencedColumnName="id"), inverseJoinColumns=@JoinColumn(
            name="id_partida",
            referencedColumnName="id"))
    private List<Partida> partidas;

    @OneToMany(mappedBy = "creador")
    private List<Mensaje> mensajes;
}
