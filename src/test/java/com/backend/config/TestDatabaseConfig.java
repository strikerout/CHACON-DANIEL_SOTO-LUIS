package com.backend.config;

import com.backend.repository.dbconnection.H2Connection;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.backend.service.impl")
public class TestDatabaseConfig {

    @PostConstruct
    public void setUpDatabase() {
        H2Connection.createTable();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
