package com.backend.dto.salida;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TurnoDtoSalida {
    private Long id;
    private LocalDateTime fechaYHora;
    private OdontologoDtoSalida odontologoDtoSalida;
    private PacienteDtoSalida pacienteDtoSalida;

    public TurnoDtoSalida(Long id, LocalDateTime fechaYHora, OdontologoDtoSalida odontologoDtoSalida, PacienteDtoSalida pacienteDtoSalida) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoDtoSalida = odontologoDtoSalida;
        this.pacienteDtoSalida = pacienteDtoSalida;
    }
}
