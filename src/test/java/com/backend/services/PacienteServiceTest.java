package com.backend.services;

import com.backend.config.TestDatabaseConfig;
import com.backend.entity.Domicilio;
import com.backend.entity.Paciente;
import com.backend.service.impl.DomicilioService;
import com.backend.service.impl.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class PacienteServiceTest {
    private PacienteService pacienteService = new PacienteService();
    private DomicilioService domicilioService = new DomicilioService();

    @Test
    void deberiaGuardarUnPacienteYRetornarElId() {
        Domicilio domicilioAGuardar = new Domicilio("Calle", 1, "Malvin", "Montevideo");
        Domicilio domicilioGuardado = domicilioService.guardarDomicilio(domicilioAGuardar);

        Paciente pacienteAGuardar = new Paciente(1234534L, "Maria", "Bonita", domicilioGuardado, LocalDate.parse("2024-05-02"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertNotNull(pacienteGuardado.getId());
    }

    @Test
    void deberiaRetornarUnaListaNoVacia() {
        assertFalse(pacienteService.listarTodosLosPacientes().isEmpty());
    }

    @Test
    void deberiaBuscarYEncontrarPacienteConElId1() {
        assertNotNull(pacienteService.buscarPaciente(1L));
    }
}
