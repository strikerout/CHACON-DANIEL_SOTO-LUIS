package com.backend.controller;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/registrar")
    public PacienteDtoSalida registarPaciente(@RequestBody PacienteDtoEntrada pacienteDtoEntrada) {
        return pacienteService.guardarPaciente(pacienteDtoEntrada);
    }

    //GET
    @GetMapping("/listar")
    public List<PacienteDtoSalida> listarPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @GetMapping("/{id}")
    public PacienteDtoSalida buscarPaciente(@PathVariable Long id) {
        return pacienteService.buscarPaciente(id);
    }




}
