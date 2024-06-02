package com.backend.controller;

import com.backend.dto.entrada.PacienteDtoEntrada;
import com.backend.dto.salida.PacienteDtoSalida;
import com.backend.entity.Paciente;
import com.backend.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {


    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    //POST
    @PostMapping("/registrar")
    public PacienteDtoSalida registarPaciente(@RequestBody PacienteDtoEntrada pacienteDtoEntrada){
        return pacienteService.guardarPaciente(pacienteDtoEntrada);
    }
    //GET
    @GetMapping("/Listar")
    public List<PacienteDtoSalida> listarPacientes(){
        return pacienteService.listarTodosLosPacientes();
    }


}
