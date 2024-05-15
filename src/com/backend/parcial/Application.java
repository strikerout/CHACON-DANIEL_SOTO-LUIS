package com.backend.parcial;

import com.backend.parcial.repository.dbconnection.H2Connection;

public class Application {
    public static void main(String[] args) {
        H2Connection.createTable();
    }
}
