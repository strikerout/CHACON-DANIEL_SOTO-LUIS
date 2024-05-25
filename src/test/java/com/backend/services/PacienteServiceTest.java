package com.backend.services;

import com.backend.config.TestDatabaseConfig;
import com.backend.entity.Paciente;
import com.backend.repository.impl.PacienteDaoH2;
import com.backend.service.impl.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class PacienteServiceTest {
    private PacienteService pacienteService;

    @Test
    void deberiaGuardarUnPacienteYRetornarElIdEnH2(){
        pacienteService = new PacienteService(new PacienteDaoH2());
        Paciente pacienteAGuardar = new Paciente( 1234534L, "Maria", "Bonita", "San juan 1324", LocalDate.parse("2024-05-02"));

        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertNotNull(pacienteGuardado.getId());
    }

    @Test
    void deberiaRetornarUnaListaNoVaciaEnH2(){
        pacienteService = new PacienteService(new PacienteDaoH2());
        assertFalse(pacienteService.listarTodosLosPacientes().isEmpty());
    }
}
