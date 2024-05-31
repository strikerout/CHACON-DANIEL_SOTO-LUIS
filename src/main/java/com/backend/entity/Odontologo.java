package com.backend.entity;

import lombok.Data;

@Data
public class Odontologo {
    private Long id;
    private Long numMatricula;
    private String nombre;
    private String apellido;

    public Odontologo(Long id, Long numMatricula, String nombre, String apellido) {
        this.id = id;
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Long numMatricula, String nombre, String apellido) {
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontólogo - Id: " + getId() + ", Número Matrícula: " + getNumMatricula() + ", Nombre: " + getNombre() + ", Apellido: " + getApellido();
    }
}
