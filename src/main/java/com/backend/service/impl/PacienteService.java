package com.backend.service.impl;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.entity.Paciente;
import com.backend.repository.IDao;
import com.backend.repository.impl.PacienteDaoH2;
import com.backend.service.IPacienteService;
import org.apache.log4j.Logger;


import java.util.List;

public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private IDao<Paciente> pacienteIDao = new PacienteDaoH2();
    private final ModelMapper modelMapper;

    @Override
    public Paciente buscarPaciente(Long id) {
        return pacienteIDao.buscar(id);
    }

    @Override
    public PacienteDtoSalida guardarPaciente(PacienteDtoEntrada pacienteDtoEntrada) {

        // pacienteIDao.guardar(paciente);
        LOGGER.info("PacienteDtoENtrada: " + pacienteDtoEntrada);
        return null;
    }

    @Override
    public List<PacienteDtoSalida> listarTodosLosPacientes() {
        return pacienteIDao.listarTodos();
    }
}
