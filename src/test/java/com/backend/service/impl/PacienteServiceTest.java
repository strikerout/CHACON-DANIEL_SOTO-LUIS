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
    void shouldSaveAPatientCorrectly() {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Estanislao Lopez", 4514, "Malvin", "Montevideo");
        PacienteDtoEntrada pacienteDtoEntrada = new PacienteDtoEntrada(12345L, "Luisito", "Soto", LocalDate.now(), domicilioEntradaDto);
        PacienteDtoSalida pacienteGuardado = pacienteService.guardarPaciente(pacienteDtoEntrada);
        assertNotNull(pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    void shouldSearchAPatientCorrectly() {
        assertDoesNotThrow(() -> pacienteService.buscarPaciente(1L));
    }

    @Test
    @Order(3)
    void shouldFailOnSearchANotExistentPatient() {
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPaciente(9999L));
    }

    @Test
    @Order(4)
    void shouldListExistentPatients() {
        assertFalse(pacienteService.listarTodosLosPacientes().isEmpty());
    }

    @Test
    @Order(5)
    void shouldDeleteAPatient() {
        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(6)
    void shouldFailOnDeleteANotExistentPatient() {
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(9999L));
    }

    @Test
    @Order(7)
    void shouldListAnEmptyListOfPatient() {
        assertTrue(pacienteService.listarTodosLosPacientes().isEmpty());
    }
}