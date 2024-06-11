package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.entity.Odontologo;
import com.backend.repository.OdontologoRepository;
import com.backend.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private final ModelMapper modelMapper;
    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(ModelMapper modelMapper, OdontologoRepository odontologoRepository) {
        this.modelMapper = modelMapper;
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public OdontologoDtoSalida buscarOdontologo(Long id) {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        if (odontologo == null) {
            return null;
        }
        return modelMapper.map(odontologo, OdontologoDtoSalida.class);
    }

    @Override
    public OdontologoDtoSalida guardarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada) {
        Odontologo odontologo = modelMapper.map(odontologoDtoEntrada, Odontologo.class);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        return modelMapper.map(odontologoGuardado, OdontologoDtoSalida.class);
    }

    @Override
    public List<OdontologoDtoSalida> listarTodosLosOdontologos() {
        List<OdontologoDtoSalida> odontologos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoDtoSalida.class))
                .toList();

        LOGGER.info("Listado de todos los odontólogos: " + odontologos);

        return odontologos;
    }

    @Override
    public OdontologoDtoSalida actualizarOdontologo(Long id, OdontologoDtoEntrada odontologoDtoEntrada) {
        Odontologo odontologoExistente = odontologoRepository.findById(id).orElse(null);
        if (odontologoExistente == null) {
            return null;
        }

        odontologoExistente.setNumMatricula(odontologoDtoEntrada.getNumMatricula());
        odontologoExistente.setNombre(odontologoDtoEntrada.getNombre());
        odontologoExistente.setApellido(odontologoDtoEntrada.getApellido());

        Odontologo odontologoActualizado = odontologoRepository.save(odontologoExistente);

        return modelMapper.map(odontologoActualizado, OdontologoDtoSalida.class);
    }



    @Override
    public void eliminarOdontologo(Long id) {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el odontólogo con ID: " + id);
        }
    }
}
