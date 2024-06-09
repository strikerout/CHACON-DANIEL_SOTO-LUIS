package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private final ModelMapper modelMapper;
    private final IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();

    public OdontologoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoDtoSalida buscarOdontologo(Long id) {
        Odontologo odontologo = odontologoIDao.buscar(id);
        if( odontologo == null){
            return null;
        }
        OdontologoDtoSalida odontologoDtoSalida = modelMapper.map(odontologo, OdontologoDtoSalida.class);

        return odontologoDtoSalida;
    }

    @Override
    public OdontologoDtoSalida guardarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada) {
        Odontologo odontologo = modelMapper.map(odontologoDtoEntrada, Odontologo.class);

        return new OdontologoDtoSalida();
    }

    @Override
    public List<OdontologoDtoSalida> listarTodosLosOdontologos() {
        List<OdontologoDtoSalida> odontologos = odontologoIDao.listarTodos()
                .stream()
                .map(odontologo -> {
                    OdontologoDtoSalida odontologoDtoSalida = modelMapper.map(odontologo, OdontologoDtoSalida.class);

                    return odontologoDtoSalida;
                })
                .toList();

        LOGGER.info("Listado de todos los odontologos: {}" + odontologos);

        return odontologos;
    }
}
