package com.backend.dto.salida;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DomicilioDtoSalida {
    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public DomicilioDtoSalida(Long id, String calle, int numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
