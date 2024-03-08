package com.example.actividad.repository;

import com.example.actividad.AbstractIntegrationDBTest;
import com.example.actividad.entities.Sugerencia;
import com.example.actividad.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SugerenciaRepositoryTest  extends AbstractIntegrationDBTest {
    SugerenciaRepository sugerenciaRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public SugerenciaRepositoryTest(SugerenciaRepository sugerenciaRepository, UsuarioRepository usuarioRepository){
        this.sugerenciaRepository = sugerenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private void initMockSugerencia(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        Sugerencia sugerencia = Sugerencia
                .builder()
                .descripcion("descripcion1")
                .usuarios(usuario)
                .build();
        sugerenciaRepository.save(sugerencia);

        Sugerencia sugerencia2 = Sugerencia
                .builder()
                .descripcion("descripcion2")
                .usuarios(usuario)
                .build();
        sugerenciaRepository.save(sugerencia2);
    }
    @BeforeEach
    void setUp() {
        sugerenciaRepository.deleteAll();
    }

    @Test
    void testSaveSugerencia(){
        initMockSugerencia();

        List<Sugerencia> sugerencias = sugerenciaRepository.findAll();

        assertThat(sugerencias.size()).isEqualTo(2);
    }
}