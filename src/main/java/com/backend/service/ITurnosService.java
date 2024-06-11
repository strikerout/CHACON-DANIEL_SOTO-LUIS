package com.backend.service;

import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.TurnoDtoSalida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnosService {
    TurnoDtoSalida buscarTurno(Long id);

    TurnoDtoSalida guardarTurno(TurnoDtoEntrada turno);

    List<TurnoDtoSalida> listarTodosLosTurnos();

    TurnoDtoSalida actualizarTurno(Long id, TurnoDtoEntrada turnoDtoEntrada);

    void eliminarTurno(Long id);
}
