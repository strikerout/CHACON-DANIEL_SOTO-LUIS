package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.entity.Odontologo;
import com.backend.repository.OdontologoRepository;
import com.backend.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private final ModelMapper modelMapper;
    private final OdontologoRepository odontologoRepository;

    public OdontologoService(ModelMapper modelMapper, OdontologoRepository odontologoRepository) {
        this.modelMapper = modelMapper;
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public OdontologoDtoSalida buscarOdontologo(Long id) {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
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
        List<OdontologoDtoSalida> odontologos = odontologoRepository.findAll()
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
