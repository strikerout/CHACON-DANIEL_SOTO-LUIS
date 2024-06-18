package com.backend.controller;

import com.backend.dto.entrada.OdontologoDtoEntrada;
import com.backend.dto.salida.OdontologoDtoSalida;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
@CrossOrigin
public class OdontologoController {

    private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // POST
    @PostMapping("/registrar")
    public OdontologoDtoSalida registrarOdontologo(@RequestBody OdontologoDtoEntrada odontologoDtoEntrada) {
        return odontologoService.guardarOdontologo(odontologoDtoEntrada);
    }

    // GET
    @GetMapping("/listar")
    public List<OdontologoDtoSalida> listarOdontologos() {
        return odontologoService.listarTodosLosOdontologos();
    }

    @GetMapping("/{id}")
    public OdontologoDtoSalida buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologo(id);
    }

    @PutMapping("/{id}")
    public OdontologoDtoSalida actualizarOdontologo(@PathVariable Long id, @RequestBody OdontologoDtoEntrada odontologoDtoEntrada) throws ResourceNotFoundException {
        return odontologoService.actualizarOdontologo(id, odontologoDtoEntrada);
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
    }
}
