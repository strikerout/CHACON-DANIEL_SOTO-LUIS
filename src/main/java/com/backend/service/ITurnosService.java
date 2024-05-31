package com.backend.service;

import com.backend.entity.Turno;

import java.util.List;

public interface ITurnosService {
    Turno buscarTurno(Long id);

    Turno guardarTurno(Turno turno);

    List<Turno> listarTodosLosTurnos();
}
