package com.backend.service;

import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.entity.Turno;

import java.util.List;

public interface ITurnosService {
    TurnoDtoSalida buscarTurno(Long id);

    TurnoDtoSalida guardarTurno(TurnoDtoEntrada turno);

    List<TurnoDtoSalida> listarTodosLosTurnos();
}
