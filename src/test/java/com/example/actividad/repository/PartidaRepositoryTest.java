package com.example.actividad.repository;

import com.example.actividad.AbstractIntegrationDBTest;
import com.example.actividad.entities.Partida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PartidaRepositoryTest extends AbstractIntegrationDBTest {

    PartidaRepository partidaRepository;

    @Autowired
    public PartidaRepositoryTest(PartidaRepository partidaRepository){
        this.partidaRepository = partidaRepository;
    }

    @BeforeEach
    void setUp() {
        partidaRepository.deleteAll();
    }

    private Partida initMockPartidas(){
        Partida partida = Partida
                .builder()
                .creador("Creador1")
                .ciudad("Ciudad1")
                .deporte("Deporte1")
                .provincia("Provincia1")
                .build();
        return partidaRepository.save(partida);
    }

    @Test
    void testSavePartida(){
        Partida partida = initMockPartidas();
        partidaRepository.save(partida);
        assertThat(partidaRepository.findById(partida.getId())).isNotEmpty();
    }

    @Test
    void testDeletePartida(){
        Partida partida = initMockPartidas();
        partidaRepository.save(partida);
        partidaRepository.delete(partida);
        assertThat(partidaRepository.findById(partida.getId())).isEmpty();
    }
}