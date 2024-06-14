package com.backend.service.impl;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.DomicilioDtoSalida;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.entity.Paciente;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.PacienteRepository;
import com.backend.service.IPacienteService;
import com.backend.utils.JsonPrinter;
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
    public PacienteDtoSalida buscarPaciente(Long id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente == null) {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }

        PacienteDtoSalida pacienteDtoSalida = modelMapper.map(paciente, PacienteDtoSalida.class);
        DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(paciente.getDomicilio(), DomicilioDtoSalida.class);
        pacienteDtoSalida.setDomicilioDtoSalida(domicilioDtoSalida);
        LOGGER.info("Paciente encontrado con éxito: " + JsonPrinter.toString(pacienteDtoSalida));
        return pacienteDtoSalida;
    }


    @Override
    public PacienteDtoSalida guardarPaciente(PacienteDtoEntrada pacienteDtoEntrada) {
        Paciente paciente = modelMapper.map(pacienteDtoEntrada, Paciente.class);
        paciente = pacienteRepository.save(paciente);

        PacienteDtoSalida pacienteDtoSalida = convertToDto(paciente);
        LOGGER.info("Paciente guardado con éxito: " + JsonPrinter.toString(pacienteDtoSalida));

        return pacienteDtoSalida;
    }


    @Override
    public List<PacienteDtoSalida> listarTodosLosPacientes() {
        List<PacienteDtoSalida> pacientes = pacienteRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}" + JsonPrinter.toString(pacientes));

        if (pacientes.isEmpty()) {
            LOGGER.warn("No se encontraron pacientes");
        }
        return pacientes;
    }

    @Override
    public PacienteDtoSalida actualizarPaciente(Long id, PacienteDtoEntrada pacienteDtoEntrada) throws ResourceNotFoundException {
        Paciente pacienteExistente = pacienteRepository.findById(id).orElse(null);
        if (pacienteExistente == null) {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }

        modelMapper.map(pacienteDtoEntrada, pacienteExistente);
        pacienteExistente = pacienteRepository.save(pacienteExistente);

        PacienteDtoSalida pacienteDtoSalida = convertToDto(pacienteExistente);
        LOGGER.info("Paciente actualizado con éxito: " + JsonPrinter.toString(pacienteDtoSalida));

        return pacienteDtoSalida;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el paciente con ID: " + id);
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }
    }

    private PacienteDtoSalida convertToDto(Paciente paciente) {
        DomicilioDtoSalida domicilioDtoSalida = modelMapper.map(paciente.getDomicilio(), DomicilioDtoSalida.class);
        PacienteDtoSalida pacienteDtoSalida = modelMapper.map(paciente, PacienteDtoSalida.class);
        pacienteDtoSalida.setDomicilioDtoSalida(domicilioDtoSalida);
        return pacienteDtoSalida;
    }
}
