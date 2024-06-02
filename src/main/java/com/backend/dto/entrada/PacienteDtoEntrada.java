package com.backend.dto.entrada;

import lombok.Data;
import java.time.LocalDate;

@Data
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

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public DomicilioEntradaDto getDomicilioEntradaDto() {
        return domicilioEntradaDto;
    }

    public void setDomicilioEntradaDto(DomicilioEntradaDto domicilioEntradaDto) {
        this.domicilioEntradaDto = domicilioEntradaDto;
    }
}
