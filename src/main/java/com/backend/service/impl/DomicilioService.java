package com.backend.service.impl;

import com.backend.entity.Domicilio;
import com.backend.repository.IDao;
import com.backend.repository.impl.DomicilioDaoH2;
import com.backend.service.IDomicilioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService implements IDomicilioService {

    private final IDao<Domicilio> domicilioIDao = new DomicilioDaoH2();

    @Override
    public Domicilio buscarDomicilio(Long id) {
        return domicilioIDao.buscar(id);
    }

    @Override
    public Domicilio guardarDomicilio(Domicilio domicilio) {
        return domicilioIDao.guardar(domicilio);
    }

    @Override
    public List<Domicilio> listarTodosLosDomicilios() {
        return domicilioIDao.listarTodos();
    }
}
