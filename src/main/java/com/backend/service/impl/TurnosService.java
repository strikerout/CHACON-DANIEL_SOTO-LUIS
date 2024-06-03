package com.backend.service.impl;

import com.backend.entity.Turno;
import com.backend.repository.IDao;
import com.backend.repository.impl.TurnoDaoH2;
import com.backend.service.ITurnosService;

import java.util.List;

public class TurnosService implements ITurnosService {

    private final IDao<Turno> turnoIDao = new TurnoDaoH2();

    @Override
    public Turno buscarTurno(Long id) {
        return turnoIDao.buscar(id);
    }

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoIDao.guardar(turno);
    }

    @Override
    public List<Turno> listarTodosLosTurnos() {
        return turnoIDao.listarTodos();
    }
}
