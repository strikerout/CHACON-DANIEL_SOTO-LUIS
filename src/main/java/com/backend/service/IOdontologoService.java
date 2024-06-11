package com.backend.service;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {
    OdontologoDtoSalida buscarOdontologo(Long id);

    OdontologoDtoSalida guardarOdontologo(OdontologoDtoEntrada odontologoDtoEntrada);

    List<OdontologoDtoSalida> listarTodosLosOdontologos();

    OdontologoDtoSalida actualizarOdontologo(Long id, OdontologoDtoEntrada odontologoDtoEntrada);

    void eliminarOdontologo(Long id);
}
