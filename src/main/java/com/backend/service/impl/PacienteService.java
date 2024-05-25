package com.backend.service.impl;

import com.backend.entity.Paciente;
import com.backend.repository.IDao;
import com.backend.service.IPacienteService;

import java.util.List;

public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public List<Paciente> listarTodosLosPacientes() {
        return pacienteIDao.listarTodos();
    }
}
