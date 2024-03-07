package com.example.actividad.repository;

import com.example.actividad.AbstractIntegrationDBTest;
import com.example.actividad.entities.Mensaje;
import com.example.actividad.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

class MensajeRepositoryTest extends AbstractIntegrationDBTest {
    MensajeRepository mensajeRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public MensajeRepositoryTest(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository){
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @BeforeEach
    void setup(){
        mensajeRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    private Usuario createUsuario(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .password("123")
                .build();
        return usuarioRepository.save(usuario);
    }

    @Test
    void givenAnMessage_whenSave_thenMessagewithId(){
        Usuario usuario = createUsuario();

        Mensaje mensaje = Mensaje.builder()
                .creador("Creador1")
                .destinatario("Destinatario1")
                .contenido("Contenido1")
                .usuario(usuario)
                .build();
        Mensaje messageSaved = mensajeRepository.save(mensaje);
        assertThat(messageSaved.getId()).isNotNull();
    }
}