package com.backend.services;

import com.backend.config.TestDatabaseConfig;
import com.backend.entity.Domicilio;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.service.impl.OdontologoService;
import com.backend.service.impl.TurnosService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class TurnoServiceTest {
    private DomicilioService domicilioService = new DomicilioService();
    private OdontologoService odontologoService = new OdontologoService();
    /*  private PacienteService pacienteService = new PacienteService(modelMapper);*/

    private TurnosService turnoService;

    @Test
    void deberiaGuardarUnTurnoYRetornarElId() {
        Odontologo odontologoAGuardar = new Odontologo(1111L, "Juan", "Perez");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);

        Domicilio domicilioAGuardar = new Domicilio("Calle", 1, "Malvin", "Montevideo");
        Domicilio domicilioGuardado = domicilioService.guardarDomicilio(domicilioAGuardar);

        Paciente pacienteAGuardar = new Paciente(1234534L, "Maria", "Bonita", domicilioGuardado, LocalDate.parse("2024-05-02"));
       /* Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        Turno turnoAGuardar = new Turno(LocalDateTime.now(), odontologoGuardado, pacienteGuardado);

        assertNotNull(turnoService.guardarTurno(turnoAGuardar).getId());*/
    }

    @Test
    void deberiaRetornarUnaListaNoVaciaDeTurnos() {
        assertFalse(domicilioService.listarTodosLosDomicilios().isEmpty());
    }

    @Test
    void deberiaBuscarYEncontrarTurnoConElId1() {
        Odontologo odontologoAGuardar = new Odontologo(1111L, "Juan", "Perez");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAGuardar);

        Domicilio domicilioAGuardar = new Domicilio("Calle", 1, "Malvin", "Montevideo");
        Domicilio domicilioGuardado = domicilioService.guardarDomicilio(domicilioAGuardar);

        Paciente pacienteAGuardar = new Paciente(1234534L, "Maria", "Bonita", domicilioGuardado, LocalDate.parse("2024-05-02"));
        /*Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        Turno turnoAGuardar = new Turno(LocalDateTime.now(), odontologoGuardado, pacienteGuardado);
        Turno turnoGuardado = turnoService.guardarTurno(turnoAGuardar);

        assertNotNull(turnoService.buscarTurno(turnoGuardado.getId()));*/
    }
}
