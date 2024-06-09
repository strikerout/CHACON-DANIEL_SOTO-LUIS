package com.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "PACIENTES")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (length = 20)
    private long dni;
    @Column (length = 50)
    private String nombre;
    @Column (length = 50)
    private String apellido;
    @Column(length = 20)
    private LocalDate fechaAlta;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;


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
