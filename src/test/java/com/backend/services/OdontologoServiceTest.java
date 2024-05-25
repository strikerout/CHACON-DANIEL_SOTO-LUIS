package com.backend.services;

import com.backend.ClinicaOdontologicaApplication;
import com.backend.config.TestDatabaseConfig;
import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.repository.impl.OdontologoDaoMemory;
import com.backend.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @Test
    void deberiaGuardarUnOdontologoYRetornarElIdEnH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologoAGuardar = new Odontologo(1111L, "Juan", "Perez");

        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
        assertNotNull(odontologoGuardado.getId());
    }

    @Test
    void deberiaRetornarUnaListaNoVaciaEnH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listarTodosLosOdontologos().isEmpty());
    }

    @Test
    void deberiaGuardarUnOdontologoYRetornarElIdEnMemory() {
        odontologoService = new OdontologoService(new OdontologoDaoMemory());
        Odontologo odontologoAGuardar = new Odontologo(1111L, "Juan", "Perez");

        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);
        assertNotNull(odontologoGuardado.getId());
    }

    @Test
    void deberiaRetornarUnaListaNoVaciaEnMemory() {
        odontologoService = new OdontologoService(new OdontologoDaoMemory());
        assertFalse(odontologoService.listarTodosLosOdontologos().isEmpty());
    }
}
