package com.backend.config;

import com.backend.repository.dbconnection.H2Connection;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestDatabaseConfig {

    @PostConstruct
    public void setUpDatabase() {
        H2Connection.createTable();
    }
}
