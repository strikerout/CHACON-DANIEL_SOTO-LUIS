package com.backend.service.impl;

import com.backend.dto.salida.DomicilioDtoSalida;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.entity.Turno;
import com.backend.repository.IDao;
import com.backend.repository.impl.TurnoDaoH2;
import com.backend.service.ITurnosService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;

public class TurnosService implements ITurnosService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private final ModelMapper modelMapper;
    private final IDao<Turno> turnoIDao = new TurnoDaoH2();

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
    public Turno guardarTurno(Turno turno) {
        return turnoIDao.guardar(turno);
    }

    @Override
    public List<Turno> listarTodosLosTurnos() {
        return turnoIDao.listarTodos();
    }
}
