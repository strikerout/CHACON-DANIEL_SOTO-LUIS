package com.backend.service.impl;

import com.backend.entity.Paciente;
import com.backend.repository.IDao;
import com.backend.repository.impl.PacienteDaoH2;
import com.backend.service.IPacienteService;

import java.util.List;

public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao = new PacienteDaoH2();

    @Override
    public Paciente buscarPaciente(Long id) {
        return pacienteIDao.buscar(id);
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
