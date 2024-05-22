package com.backend.service;

import com.backend.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    List<Odontologo> listarTodosLosOdontologos();
}
