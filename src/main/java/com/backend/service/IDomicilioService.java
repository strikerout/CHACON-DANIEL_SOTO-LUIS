package com.backend.service;

import com.backend.entity.Domicilio;

import java.util.List;

public interface IDomicilioService {

    Domicilio buscarDomicilio(Long id);

    Domicilio guardarDomicilio(Domicilio domicilio);

    List<Domicilio> listarTodosLosDomicilios();
}
