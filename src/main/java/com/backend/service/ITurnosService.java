package com.backend.service;

import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnosService {
    TurnoDtoSalida buscarTurno(Long id) throws ResourceNotFoundException;

    TurnoDtoSalida guardarTurno(TurnoDtoEntrada turno) throws ResourceNotFoundException;

    List<TurnoDtoSalida> listarTodosLosTurnos();

    TurnoDtoSalida actualizarTurno(Long id, TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
