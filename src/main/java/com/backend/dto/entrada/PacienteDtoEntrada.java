package com.backend.dto.entrada;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PacienteDtoEntrada {
    private long dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaAlta;
    private DomicilioEntradaDto domicilioEntradaDto;

    public PacienteDtoEntrada(long dni, String nombre, String apellido, LocalDate fechaAlta, DomicilioEntradaDto domicilioEntradaDto) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaAlta = fechaAlta;
        this.domicilioEntradaDto = domicilioEntradaDto;
    }
}
