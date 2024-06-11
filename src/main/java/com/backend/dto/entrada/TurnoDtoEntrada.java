package com.backend.dto.entrada;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TurnoDtoEntrada {
    private Long id;
    private LocalDateTime fechaYHora;
    private Long odontologoId; // Cambiado a Long para almacenar solo el ID
    private Long pacienteId; // Cambiado a Long para almacenar solo el ID

    public TurnoDtoEntrada(LocalDateTime fechaYHora, Long odontologoId, Long pacienteId) {
        this.fechaYHora = fechaYHora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }
}
