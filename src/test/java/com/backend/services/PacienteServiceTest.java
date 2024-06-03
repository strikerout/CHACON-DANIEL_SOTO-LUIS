package com.backend.services;

import com.backend.config.TestDatabaseConfig;
import com.backend.dto.entrada.DomicilioEntradaDto;
import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.entity.Domicilio;
import com.backend.entity.Paciente;
import com.backend.repository.impl.PacienteDaoH2;
import com.backend.service.IPacienteService;
import com.backend.service.impl.DomicilioService;
import com.backend.service.impl.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class PacienteServiceTest {

    private final IPacienteService pacienteService;

    public PacienteServiceTest(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Test
    void deberiaGuardarUnPacienteYRetornarElId() {
        DomicilioEntradaDto domicilioDtoAGuardar = new DomicilioEntradaDto("Calle", 1, "Malvin", "Montevideo");
        PacienteDtoEntrada pacienteDtoAGuardar = new PacienteDtoEntrada(1234534L, "Maria", "Bonita", LocalDate.parse("2024-05-02"), domicilioDtoAGuardar);

        PacienteDtoSalida pacienteGuardado = pacienteService.guardarPaciente(pacienteDtoAGuardar);
        assertNotNull(pacienteGuardado.getId());
    }

   /* @Test
    void deberiaRetornarUnaListaNoVacia() {
        assertFalse(pacienteService.listarTodosLosPacientes().isEmpty());
    }

    @Test
    void deberiaBuscarYEncontrarPacienteConElId1() {
        assertNotNull(pacienteService.buscarPaciente(1L));
    }*/
}
