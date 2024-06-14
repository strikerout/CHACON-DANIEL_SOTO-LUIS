package com.backend.service;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {
    PacienteDtoSalida buscarPaciente(Long id) throws ResourceNotFoundException;

    PacienteDtoSalida guardarPaciente(PacienteDtoEntrada pacienteDtoEntrada);

    List<PacienteDtoSalida> listarTodosLosPacientes();

    PacienteDtoSalida actualizarPaciente(Long id, PacienteDtoEntrada pacienteDtoEntrada) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
