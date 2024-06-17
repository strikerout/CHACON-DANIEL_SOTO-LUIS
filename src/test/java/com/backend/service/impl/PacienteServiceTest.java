package com.backend.service.impl;

import com.backend.dto.entrada.DomicilioEntradaDto;
import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.IPacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaGuardarUnPacienteCorrectamente() {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Estanislao Lopez", 4514, "Malvin", "Montevideo");
        PacienteDtoEntrada pacienteDtoEntrada = new PacienteDtoEntrada(12345L, "Luisito", "Soto", LocalDate.now(), domicilioEntradaDto);
        PacienteDtoSalida pacienteGuardado = pacienteService.guardarPaciente(pacienteDtoEntrada);
        assertNotNull(pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnPacienteCorrectamente() {
        assertDoesNotThrow(() -> pacienteService.buscarPaciente(1L));
    }

    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnPacienteInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPaciente(9999L));
    }

    @Test
    @Order(4)
    void deberiaListarPacientesExistentes() {
        assertFalse(pacienteService.listarTodosLosPacientes().isEmpty());
    }

    @Test
    @Order(5)
    void deberiaEliminarUnPaciente() {
        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(6)
    void deberiaFallarAlEliminarUnPacienteInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(9999L));
    }

    @Test
    @Order(7)
    void deberiaListarUnaListaVaciaDePacientes() {
        assertTrue(pacienteService.listarTodosLosPacientes().isEmpty());
    }
}
