package com.backend.dto.salida;

import com.backend.entity.Domicilio;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDtoSalida {
    private long dni;
    private String nombre;
    private String apellido;
    private DomicilioDtoSalida domicilioDtoSalida;
    private LocalDate fechaAlta;

    public PacienteDtoSalida(long dni, String nombre, String apellido, DomicilioDtoSalida domicilioDtoSalida, LocalDate fechaAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilioDtoSalida = domicilioDtoSalida;
        this.fechaAlta = fechaAlta;
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

    public DomicilioDtoSalida getDomicilioDtoSalida() {
        return domicilioDtoSalida;
    }

    public void setDomicilioDtoSalida(DomicilioDtoSalida domicilioDtoSalida) {
        this.domicilioDtoSalida = domicilioDtoSalida;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
