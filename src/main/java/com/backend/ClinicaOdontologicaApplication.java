package com.backend;

import com.backend.repository.dbconnection.H2Connection;
import com.backend.repository.impl.OdontologoDaoMemory;
import jakarta.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoMemory.class);

    public static void main(String[] args) {
        SpringApplication.run(ClinicaOdontologicaApplication.class, args);
        H2Connection.createTable();
        LOGGER.info("Application works :)");
        LOGGER.info("Created by: Chacón Daniel & Soto Luis :)");
    }
}
