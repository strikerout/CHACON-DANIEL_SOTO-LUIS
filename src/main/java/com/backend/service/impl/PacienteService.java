package com.backend.service.impl;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.DomicilioDtoSalida;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.entity.Paciente;
import com.backend.repository.PacienteRepository;
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
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PacienteDtoSalida buscarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
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
        paciente = pacienteRepository.save(paciente); // Aquí guardamos el paciente en la base de datos
        DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(paciente.getDomicilio(), DomicilioDtoSalida.class);
        return PacienteDtoSalida.fromPacienteAndDomicilio(paciente, domicilioDtoSalida);
    }


    @Override
    public List<PacienteDtoSalida> listarTodosLosPacientes() {
        List<PacienteDtoSalida> pacientes = pacienteRepository.findAll()
                .stream()
                .map(paciente -> {
                    DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(paciente.getDomicilio(), DomicilioDtoSalida.class);
                    return PacienteDtoSalida.fromPacienteAndDomicilio(paciente, domicilioDtoSalida);
                })
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}" + pacientes);

        return pacientes;
    }

    @Override
    public PacienteDtoSalida actualizarPaciente(Long id, PacienteDtoEntrada pacienteDtoEntrada) {
        Paciente pacienteExistente = pacienteRepository.findById(id).orElse(null);
        if (pacienteExistente == null) {
            LOGGER.warn("No se encontró el paciente con ID: " + id);
        }

        modelMapper.map(pacienteDtoEntrada, pacienteExistente);
        pacienteExistente = pacienteRepository.save(pacienteExistente);

        PacienteDtoSalida pacienteDtoSalida = modelMapper.map(pacienteExistente, PacienteDtoSalida.class);
        DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(pacienteExistente.getDomicilio(), DomicilioDtoSalida.class);
        pacienteDtoSalida.setDomicilioDtoSalida(domicilioDtoSalida);

        return pacienteDtoSalida;
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el paciente con ID: " + id);
        }
    }
}
