package com.backend.service.impl;

import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.repository.OdontologoRepository;
import com.backend.repository.PacienteRepository;
import com.backend.repository.TurnoRepository;
import com.backend.service.ITurnosService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnosService implements ITurnosService {

    private static final Logger LOGGER = Logger.getLogger(TurnosService.class);
    private final TurnoRepository turnoRepository;
    private final OdontologoRepository odontologoRepository;
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TurnosService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoDtoSalida buscarTurno(Long id) {
        Turno turno = turnoRepository.findById(id).orElse(null);
        if (turno == null) {
            return null;
        }
        return modelMapper.map(turno, TurnoDtoSalida.class);
    }

    @Override
    public TurnoDtoSalida guardarTurno(TurnoDtoEntrada turnoDtoEntrada) {
        Turno turnoAGuardar = new Turno();
        turnoAGuardar.setFechaYHora(turnoDtoEntrada.getFechaYHora());

        Odontologo odontologo = odontologoRepository.findById(turnoDtoEntrada.getOdontologoId()).orElse(null);
        Paciente paciente = pacienteRepository.findById(turnoDtoEntrada.getPacienteId()).orElse(null);

        if (odontologo == null || paciente == null) {
            LOGGER.warn("No se encontró el el odontologo o el paciente");
            return null;
        }

        turnoAGuardar.setOdontologo(odontologo);
        turnoAGuardar.setPaciente(paciente);

        Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
        return modelMapper.map(turnoGuardado, TurnoDtoSalida.class);
    }


    @Override
    public List<TurnoDtoSalida> listarTodosLosTurnos() {
        List<TurnoDtoSalida> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoDtoSalida.class))
                .toList();

        LOGGER.info("Listado de todos los turnos: {}" + turnos);

        return turnos;
    }

    @Override
    public TurnoDtoSalida actualizarTurno(Long id, TurnoDtoEntrada turnoDtoEntrada) {
        Turno turnoExistente = turnoRepository.findById(id).orElse(null);
        if (turnoExistente == null) {
            return null;
        }

        turnoExistente.setFechaYHora(turnoDtoEntrada.getFechaYHora());

        Odontologo odontologo = odontologoRepository.findById(turnoDtoEntrada.getOdontologoId()).orElse(null);
        if (odontologo == null) {
            LOGGER.warn("Odontólogo no encontrado");
        }
        turnoExistente.setOdontologo(odontologo);

        Paciente paciente = pacienteRepository.findById(turnoDtoEntrada.getPacienteId()).orElse(null);
        if (paciente == null) {
           LOGGER.warn("Paciente no encontrado");
        }
        turnoExistente.setPaciente(paciente);
        turnoExistente = turnoRepository.save(turnoExistente);

        return modelMapper.map(turnoExistente, TurnoDtoSalida.class);
    }


    @Override
    public void eliminarTurno(Long id) {
        if (turnoRepository.existsById(id)) {
            turnoRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el turno con ID: " + id);
        }
    }
}
