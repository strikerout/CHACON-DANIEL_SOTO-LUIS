package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    IOdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaGuardarUnOdontologoCorrectamente() {
        OdontologoDtoEntrada odontologoDtoEntrada = new OdontologoDtoEntrada(112233L, "Daniel", "Chacon");
        OdontologoDtoSalida odontologoGuardado = odontologoService.guardarOdontologo(odontologoDtoEntrada);
        assertNotNull(odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnOdontologoCorrectamente() {
        assertDoesNotThrow(() -> odontologoService.buscarOdontologo(1L));
    }

    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnOdontologoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologo(9999L));
    }

    @Test
    @Order(4)
    void deberiaListarOdontologosExistentes() {
        assertFalse(odontologoService.listarTodosLosOdontologos().isEmpty());
    }

    @Test
    @Order(5)
    void deberiaEliminarUnOdontologo() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(6)
    void deberiaFallarAlEliminarUnOdontologoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(9999L));
    }

    @Test
    @Order(7)
    void deberiaListarUnaListaVaciaDeOdontologos() {
        assertTrue(odontologoService.listarTodosLosOdontologos().isEmpty());
    }
}
