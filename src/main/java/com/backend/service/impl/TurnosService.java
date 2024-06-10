package com.backend.service.impl;

import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.entity.Turno;
import com.backend.repository.impl.TurnoDaoH2;
import com.backend.service.ITurnosService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;

public class TurnosService implements ITurnosService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private final ModelMapper modelMapper;
    private final IDao<Turno> turnoIDao;

    public TurnosService(IDao<Turno> turnoIDao, ModelMapper modelMapper ) {
        this.modelMapper = modelMapper;
        this.turnoIDao = turnoIDao;
    }

    @Override
    public TurnoDtoSalida buscarTurno(Long id) {
        Turno turno = turnoIDao.buscar(id);
        if (turno == null){
            return null;
        }

        TurnoDtoSalida turnoDtoSalida = modelMapper.map(turno, TurnoDtoSalida.class);

        return turnoDtoSalida;
    }

    @Override
    public TurnoDtoSalida guardarTurno(TurnoDtoEntrada turno) {
        Turno turnoAGuardar =  modelMapper.map(turno, Turno.class);
        Turno turnoGuardado = turnoIDao.guardar(turnoAGuardar);
        TurnoDtoSalida turnoDtoSalida = modelMapper.map(turnoGuardado, TurnoDtoSalida.class);
        return turnoDtoSalida;
    }

    @Override
    public List<TurnoDtoSalida> listarTodosLosTurnos() {
        List<TurnoDtoSalida> turnos = turnoIDao.listarTodos()
                .stream()
                .map(turno -> {
                    TurnoDtoSalida turnoDtoSalida = modelMapper.map(turno, TurnoDtoSalida.class);

                    return turnoDtoSalida;
                })
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}" + turnos);

        return turnos;
    }
}
