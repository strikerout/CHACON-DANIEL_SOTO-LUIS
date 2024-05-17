package com.backend.parcial.repository;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);

    List<T> listarTodos();
}
