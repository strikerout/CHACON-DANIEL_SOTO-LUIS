package com.backend.controller;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.service.IPacienteService;
import com.backend.service.ITurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turnos")
public class TurnoController {

    private ITurnosService turnoService;

    @Autowired
    public TurnoController(ITurnosService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @PostMapping("/registrar")
    public TurnoDtoSalida registarTurno(@RequestBody TurnoDtoEntrada turnoDtoEntrada) {
        return turnoService.guardarTurno(turnoDtoEntrada);
    }

    //GET
    @GetMapping("/listar")
    public List<TurnoDtoSalida> listarTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @GetMapping("/{id}")
    public TurnoDtoSalida buscarTurno(@PathVariable Long id) {
        return turnoService.buscarTurno(id);
    }

    @PutMapping("/{id}")
    public TurnoDtoSalida actualizarTurno(@PathVariable Long id, @RequestBody TurnoDtoEntrada turnoDtoEntrada) {
        return turnoService.actualizarTurno(id, turnoDtoEntrada);
    }

    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
    }
}