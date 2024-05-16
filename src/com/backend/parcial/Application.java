package com.backend.parcial;

import com.backend.parcial.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class);
    public static void main(String[] args) {
        H2Connection.createTable();
        logger.info("Application works :)");
    }
}
