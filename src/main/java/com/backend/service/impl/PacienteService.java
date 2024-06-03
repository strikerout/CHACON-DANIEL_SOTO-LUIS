package com.backend.service.impl;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.DomicilioDtoSalida;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.entity.Paciente;
import com.backend.repository.IDao;
import com.backend.service.IPacienteService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private final ModelMapper modelMapper;
    private IDao<Paciente> pacienteIDao;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public PacienteDtoSalida buscarPaciente(Long id) {
        Paciente paciente = pacienteIDao.buscar(id);
        if (paciente == null) {
            // Manejar la situación donde no se encontró el paciente con el ID especificado
            return null;
        }

        PacienteDtoSalida pacienteDtoSalida = modelMapper.map(paciente, PacienteDtoSalida.class);
        DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(paciente.getDomicilio(), DomicilioDtoSalida.class);
        pacienteDtoSalida.setDomicilioDtoSalida(domicilioDtoSalida);

        return pacienteDtoSalida;
    }


    @Override
    public PacienteDtoSalida guardarPaciente(PacienteDtoEntrada pacienteDtoEntrada) {
        Paciente paciente = modelMapper.map(pacienteDtoEntrada, Paciente.class);
        paciente = pacienteIDao.guardar(paciente); // Aquí guardamos el paciente en la base de datos
        DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(paciente.getDomicilio(), DomicilioDtoSalida.class);
        return PacienteDtoSalida.fromPacienteAndDomicilio(paciente, domicilioDtoSalida);
    }


    @Override
    public List<PacienteDtoSalida> listarTodosLosPacientes() {
        List<PacienteDtoSalida> pacientes = pacienteIDao.listarTodos()
                .stream()
                .map(paciente -> {
                    PacienteDtoSalida pacienteDtoSalida = modelMapper.map(paciente, PacienteDtoSalida.class);
                    DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(paciente.getDomicilio(), DomicilioDtoSalida.class);
                    pacienteDtoSalida.setDomicilioDtoSalida(domicilioDtoSalida);
                    return pacienteDtoSalida;
                })
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}" + pacientes);

        return pacientes;
    }

}
