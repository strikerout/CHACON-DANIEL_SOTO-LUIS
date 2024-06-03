package com.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class Paciente {
    private long id;
    private long dni;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private LocalDate fechaAlta;

    public Paciente(long id, long dni, String nombre, String apellido, Domicilio domicilio, LocalDate fechaAlta) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
    }

    public Paciente(long dni, String nombre, String apellido, Domicilio domicilio, LocalDate fechaAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
    }


    @Override
    public String toString() {
        return "Paciente - ID: " + getId() + ",DNI : " + getDni() + ",Nombre : " + getNombre() + ",Apellido : " + getApellido() + ",Domicilio : " + getDomicilio() + ",Fecha Alta : " + getFechaAlta();
    }
}
