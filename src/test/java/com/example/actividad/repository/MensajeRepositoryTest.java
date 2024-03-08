package com.example.actividad.repository;

import com.example.actividad.AbstractIntegrationDBTest;
import com.example.actividad.entities.Mensaje;
import com.example.actividad.entities.Usuario;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MensajeRepositoryTest extends AbstractIntegrationDBTest {
    MensajeRepository mensajeRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public MensajeRepositoryTest(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository){
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    void initMockMessages(){
        Usuario usuario = createUsuario();
        Mensaje mensaje = Mensaje.builder()
                .creador("Creador1")
                .destinatario("Destinatario1")
                .contenido("Contenido1")
                .usuario(usuario)
                .build();
        mensajeRepository.save(mensaje);

        Mensaje mensaje2 = Mensaje.builder()
                .creador("Creador2")
                .destinatario("Destinatario2")
                .contenido("Contenido2")
                .usuario(usuario)
                .build();
        mensajeRepository.save(mensaje2);
        mensajeRepository.flush();
    }
    @BeforeEach
    void setup(){
        mensajeRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    private @NotNull Usuario createUsuario(){
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
        mensajeRepository.flush();
        assertThat(messageSaved.getId()).isNotNull();
    }

    @Test
    void shouldGetAllMessages(){
        initMockMessages();
        List<Mensaje> messages = mensajeRepository.findAll();

        assertThat(messages).hasSize(2);
    }

    @Test
    void givenMessages_whenBuscarPorCreador_thenObtienesUnaListaDeMensajes(){
        initMockMessages();
        List<Mensaje> messages = mensajeRepository.findByCreador("Creador1");

        assertThat(messages).isNotEmpty();
        assertThat(messages).first().hasFieldOrPropertyWithValue("creador","Creador1");
    }
}