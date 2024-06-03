package com.backend.dto.salida;

import com.backend.entity.Paciente;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PacienteDtoSalida {
    private long id;
    private long dni;
    private String nombre;
    private String apellido;
    private DomicilioDtoSalida domicilioDtoSalida;
    private LocalDate fechaAlta;

    public PacienteDtoSalida(Long id, long dni, String nombre, String apellido, DomicilioDtoSalida domicilioDtoSalida, LocalDate fechaAlta) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilioDtoSalida = domicilioDtoSalida;
        this.fechaAlta = fechaAlta;
    }

    // Método estático para construir PacienteDtoSalida con DomicilioDtoSalida
    public static PacienteDtoSalida fromPacienteAndDomicilio(Paciente paciente, DomicilioDtoSalida domicilioDtoSalida) {
        return new PacienteDtoSalida(
                paciente.getId(),
                paciente.getDni(),
                paciente.getNombre(),
                paciente.getApellido(),
                domicilioDtoSalida,
                paciente.getFechaAlta()
        );
    }
}
