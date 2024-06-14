package com.backend.dto.salida;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PacienteDtoSalida {
    private Long id;
    private Long dni;
    private String nombre;
    private String apellido;
    private DomicilioDtoSalida domicilioDtoSalida;
    private LocalDate fechaAlta;

    public PacienteDtoSalida(Long id, Long dni, String nombre, String apellido, DomicilioDtoSalida domicilioDtoSalida, LocalDate fechaAlta) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilioDtoSalida = domicilioDtoSalida;
        this.fechaAlta = fechaAlta;
    }
}
