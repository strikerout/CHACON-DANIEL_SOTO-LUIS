package com.backend.dto.salida;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OdontologoDtoSalida {
    private Long id;
    private Long numMatricula;
    private String nombre;
    private String apellido;

    public OdontologoDtoSalida(Long id, Long numMatricula, String nombre, String apellido) {
        this.id = id;
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
