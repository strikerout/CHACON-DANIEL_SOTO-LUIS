package com.backend.entity;

import java.time.LocalDate;


public class Paciente {
    private long id;
    private long dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private LocalDate fechaAlta;

    public Paciente(long id, long dni, String nombre, String apellido, String domicilio, LocalDate fechaAlta) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
    }

    public Paciente(long dni, String nombre, String apellido, String domicilio, LocalDate fechaAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString(){
        return "Paciente - ID: " + getId() + ",DNI : " + getDni() + ",Nombre : " + getNombre() + ",Apellido : " + getApellido() + ",Domicilio : "+ getDomicilio() + ",Fecha Alta : " + getFechaAlta();
    }
}
