package com.backend.entity;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(Long numMatricula) {
        this.numMatricula = numMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontólogo - Id: " + getId() + ", Número Matrícula: " + getNumMatricula() + ", Nombre: " + getNombre() + ", Apellido: " + getApellido();
    }
}
