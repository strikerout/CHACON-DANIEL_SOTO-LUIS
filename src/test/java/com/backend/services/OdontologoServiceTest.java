package com.backend.services;

import com.backend.config.TestDatabaseConfig;
import com.backend.entity.Odontologo;
import com.backend.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class OdontologoServiceTest {
    private OdontologoService odontologoService = new OdontologoService();

    @Test
    void deberiaGuardarUnOdontologoYRetornarElId() {
        Odontologo odontologoAGuardar = new Odontologo(1111L, "Juan", "Perez");

        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
        assertNotNull(odontologoGuardado.getId());
    }

    @Test
    void deberiaRetornarUnaListaNoVacia() {
        assertFalse(odontologoService.listarTodosLosOdontologos().isEmpty());
    }

    @Test
    void deberiaBuscarYEncontrarOdontologoConElId1() {
        assertNotNull(odontologoService.buscarOdontologo(1L));
    }
}
