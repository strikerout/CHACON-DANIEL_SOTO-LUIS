package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.entity.Odontologo;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.OdontologoRepository;
import com.backend.service.IOdontologoService;
import com.backend.utils.JsonPrinter;
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
    public OdontologoDtoSalida buscarOdontologo(Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        if (odontologo == null) {
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + id);
        }
        LOGGER.info("Odontólogo encontrado con éxito: " + JsonPrinter.toString(odontologo));
        return modelMapper.map(odontologo, OdontologoDtoSalida.class);
    }

    @Override
    public OdontologoDtoSalida guardarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada) {
        Odontologo odontologo = modelMapper.map(odontologoDtoEntrada, Odontologo.class);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        LOGGER.info("Odontólogo guardado con éxito: " + JsonPrinter.toString(odontologoGuardado));
        return modelMapper.map(odontologoGuardado, OdontologoDtoSalida.class);
    }

    @Override
    public List<OdontologoDtoSalida> listarTodosLosOdontologos() {
        List<OdontologoDtoSalida> odontologos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoDtoSalida.class))
                .toList();

        LOGGER.info("Listado de todos los odontólogos: " + JsonPrinter.toString(odontologos));

        if (odontologos.isEmpty()) {
            LOGGER.warn("No se encontraron odontólogos");
        }

        return odontologos;
    }

    @Override
    public OdontologoDtoSalida actualizarOdontologo(Long id, OdontologoDtoEntrada odontologoDtoEntrada) throws ResourceNotFoundException {
        Odontologo odontologoExistente = odontologoRepository.findById(id).orElse(null);
        if (odontologoExistente == null) {
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + id);
        }

        odontologoExistente.setNumMatricula(odontologoDtoEntrada.getNumMatricula());
        odontologoExistente.setNombre(odontologoDtoEntrada.getNombre());
        odontologoExistente.setApellido(odontologoDtoEntrada.getApellido());

        Odontologo odontologoActualizado = odontologoRepository.save(odontologoExistente);
        LOGGER.info("Odontólogo actualizado con éxito: " + JsonPrinter.toString(odontologoActualizado));

        return modelMapper.map(odontologoActualizado, OdontologoDtoSalida.class);
    }


    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el odontólogo con ID: " + id);
            throw new ResourceNotFoundException("No existe registro de odontologo con id " + id);
        }
    }
}
