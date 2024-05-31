package com.backend.repository;

import java.util.List;

public interface IDao<T> {

    T buscar(Long id);

    T guardar(T t);

    List<T> listarTodos();


}
