package com.backend.controller;

import com.backend.dto.entrada.TurnoDtoEntrada;
import com.backend.dto.salida.TurnoDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turnos")
@CrossOrigin
public class TurnoController {

    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @PostMapping("/registrar")
    public TurnoDtoSalida registarTurno(@RequestBody TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException {
        return turnoService.guardarTurno(turnoDtoEntrada);
    }

    //GET
    @GetMapping("/listar")
    public List<TurnoDtoSalida> listarTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @GetMapping("/{id}")
    public TurnoDtoSalida buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoService.buscarTurno(id);
    }

    @PutMapping("/{id}")
    public TurnoDtoSalida actualizarTurno(@PathVariable Long id, @RequestBody TurnoDtoEntrada turnoDtoEntrada) throws ResourceNotFoundException {
        return turnoService.actualizarTurno(id, turnoDtoEntrada);
    }

    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
    }
}