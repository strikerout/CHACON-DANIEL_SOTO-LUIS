package com.backend.parcial.test;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.impl.OdontologoDaoH2;
import com.backend.parcial.repository.impl.OdontologoDaoMemory;
import com.backend.parcial.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
