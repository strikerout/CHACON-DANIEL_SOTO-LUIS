package com.backend.service.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private final IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();

    @Override
    public Odontologo buscarOdontologo(Long id) {
        return odontologoIDao.buscar(id);
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodosLosOdontologos() {
        return odontologoIDao.listarTodos();
    }
}
