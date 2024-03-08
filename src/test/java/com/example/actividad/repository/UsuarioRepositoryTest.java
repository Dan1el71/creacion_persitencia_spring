package com.example.actividad.repository;

import com.example.actividad.AbstractIntegrationDBTest;
import com.example.actividad.entities.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class UsuarioRepositoryTest extends AbstractIntegrationDBTest {
    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    void initMockUsuarios(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        Usuario usuario2 = Usuario.builder().nombre("Jose")
                .apellido("Pertuz")
                .username("josepertuz")
                .password("123")
                .build();
        usuarioRepository.save(usuario2);
        usuarioRepository.flush();
    }

    @BeforeEach
    void setUp() {

        usuarioRepository.deleteAll();

    }
    @Test
    void givenAnUser_whenSave_thenUserwithId(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .password("123")
                .build();
        Usuario userSaved = usuarioRepository.save(usuario);
        assertThat(userSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("dado un conjunto de usuarios al buscarlo todos obtenemos la lista de los usuarios en la base de datos")
    void shouldGetAllUsers(){
        initMockUsuarios();
        List<Usuario> usuarios = usuarioRepository.findAll();

        assertThat(usuarios).hasSize(2);
    }
    @Test
    void givenUsuarios_whenBuscarPorNombreyApellido_thenObtienesUnaListaDeUsuarios(){
        initMockUsuarios();
        List<Usuario> usuarios = usuarioRepository.findByNombreAndApellido("Julian", "Pizarro");

        assertThat(usuarios).isNotEmpty();
        assertThat(usuarios).first().hasFieldOrPropertyWithValue("nombre","Julian");
    }
}