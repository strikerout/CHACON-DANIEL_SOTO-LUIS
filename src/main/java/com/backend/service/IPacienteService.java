package com.backend.service;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente buscarPaciente(Long id);

    PacienteDtoSalida guardarPaciente(PacienteDtoEntrada pacienteDtoEntrada);

    List<PacienteDtoSalida> listarTodosLosPacientes();
}
