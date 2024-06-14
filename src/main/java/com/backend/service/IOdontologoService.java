package com.backend.service;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {
    OdontologoDtoSalida buscarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoDtoSalida guardarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada);

    List<OdontologoDtoSalida> listarTodosLosOdontologos();

    OdontologoDtoSalida actualizarOdontologo(Long id, OdontologoDtoEntrada odontologoDtoEntrada) throws ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
