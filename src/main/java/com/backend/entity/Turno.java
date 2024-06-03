package com.backend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Turno {
    private Long id;
    private LocalDateTime fechaYHora;
    private Odontologo odontologo;
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
