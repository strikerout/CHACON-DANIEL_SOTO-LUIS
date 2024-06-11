package com.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table (name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 20)
    private Long numMatricula;
    @Column (length = 50)
    private String nombre;
    @Column (length = 50)
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
