package com.backend.service;

import com.backend.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);

    List<Paciente> listarTodosLosPacientes();
}
