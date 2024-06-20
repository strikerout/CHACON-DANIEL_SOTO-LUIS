package com.backend.service.impl;

import com.backend.dto.entrada.DomicilioEntradaDto;
import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.IOdontologoService;
import com.backend.service.IPacienteService;
import com.backend.service.ITurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TurnosServiceTest {

    @Autowired
    ITurnoService turnoService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    IPacienteService pacienteService;

    private Long odontologoId;
    private Long pacienteId;

    @BeforeAll
    void setup() {
        // Crear y guardar un odontólogo si no existe
        if (odontologoId == null) {
            OdontologoDtoEntrada odontologoDtoEntrada = new OdontologoDtoEntrada(112233L, "Daniel", "Chacon");
            OdontologoDtoSalida odontologoGuardado = odontologoService.guardarOdontologo(odontologoDtoEntrada);
            assertNotNull(odontologoGuardado.getId());
            odontologoId = odontologoGuardado.getId();
        }

        // Crear y guardar un paciente si no existe
        if (pacienteId == null) {
            DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Estanislao Lopez", 4514, "Malvin", "Montevideo");
            PacienteDtoEntrada pacienteDtoEntrada = new PacienteDtoEntrada(12345L, "Luisito", "Soto", LocalDate.now(), domicilioEntradaDto);
            PacienteDtoSalida pacienteGuardado = pacienteService.guardarPaciente(pacienteDtoEntrada);
            assertNotNull(pacienteGuardado.getId());
            pacienteId = pacienteGuardado.getId();
        }
    }

    @Test
    @Order(1)
    void deberiaGuardarUnTurnoCorrectamente() {
        TurnoDtoEntrada turnoDtoEntrada = new TurnoDtoEntrada(LocalDateTime.now(), odontologoId, pacienteId);
        assertDoesNotThrow(() -> {
            TurnoDtoSalida turnoGuardado = turnoService.guardarTurno(turnoDtoEntrada);
            assertNotNull(turnoGuardado.getId());
        });
    }

    @Test
    @Order(2)
    void deberiaBuscarUnTurnoCorrectamente() {
        assertDoesNotThrow(() -> turnoService.buscarTurno(1L));
    }

    @Test
    @Order(3)
    void deberiaFallarAlBuscarUnTurnoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurno(9999L));
    }

    @Test
    @Order(4)
    void deberiaListarTurnosExistentes() {
        assertFalse(turnoService.listarTodosLosTurnos().isEmpty());
    }

    @Test
    @Order(5)
    void deberiaEliminarUnTurno()  {
        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(6)
    void deberiaFallarAlEliminarUnTurnoInexistente() {
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(9999L));
    }

    @Test
    @Order(7)
    void deberiaListarUnaListaVaciaDeTurnos() {
        assertTrue(turnoService.listarTodosLosTurnos().isEmpty());
    }
}
