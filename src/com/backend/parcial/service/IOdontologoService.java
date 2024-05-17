package com.backend.parcial.service;

import com.backend.parcial.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    List<Odontologo> listarTodosLosOdontologos();
}
