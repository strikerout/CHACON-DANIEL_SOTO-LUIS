package com.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Dani revisa esto aca no se si es leng o el tipo de dato.!!
    @Column (length = 20)
    private LocalDateTime fechaYHora;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Turno(Long id, LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Turno(LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fechaYHora=" + fechaYHora +
                ", odontologo=" + odontologo +
                ", paciente=" + paciente +
                '}';
    }
}
