package com.backend.services;

import com.backend.config.TestDatabaseConfig;
import com.backend.entity.Domicilio;
import com.backend.service.impl.DomicilioService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class DomicilioServiceTest {
    private DomicilioService domicilioService = new DomicilioService();

    @Test
    void deberiaGuardarUnDomicilioYRetornarElId() {
        Domicilio domicilioAGuardar = new Domicilio("Calle", 1, "Malvin", "Montevideo");

        assertNotNull(domicilioService.guardarDomicilio(domicilioAGuardar).getId());
    }

    @Test
    void deberiaRetornarUnaListaNoVaciaDeDomicilios() {
        assertFalse(domicilioService.listarTodosLosDomicilios().isEmpty());
    }

    @Test
    void deberiaBuscarYEncontrarDomicilioConElId1() {
        assertNotNull(domicilioService.buscarDomicilio(1L));
    }
}
