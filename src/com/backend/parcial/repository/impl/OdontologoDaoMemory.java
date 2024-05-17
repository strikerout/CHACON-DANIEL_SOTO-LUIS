package com.backend.parcial.repository.impl;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemory implements IDao<Odontologo> {

    List<Odontologo> listaOdontologos = new ArrayList<>();

    public OdontologoDaoMemory() {
        Odontologo odontologo1 = new Odontologo( 1L,123654L ,"Pedro", "Estocolmo" );
        Odontologo odontologo2 = new Odontologo( 2L,124654L ,"Maria", "Barcelona" );
        Odontologo odontologo3 = new Odontologo( 3L,125654L ,"Fernanda", "Paris" );
        Odontologo odontologo4 = new Odontologo( 4L,126654L ,"Diego", "Monterrey" );

        listaOdontologos.add(odontologo1);
        listaOdontologos.add(odontologo2);
        listaOdontologos.add(odontologo3);
        listaOdontologos.add(odontologo4);
    }


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Long id = Long.valueOf (listaOdontologos.size()+1);
        Odontologo odontologoAGuardar = new Odontologo(id, odontologo.getNumMatricula(), odontologo.getNombre(), odontologo.getApellido());
        return odontologoAGuardar;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return listaOdontologos;
    }
}
