package com.backend.repository.dbconnection;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/clinica", "sa", "sa");
    }

    public static void createTable() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/clinica", "sa", "sa");

            // Load the script content from the classpath
            ClassPathResource resource = new ClassPathResource("create.sql");
            String script = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));

            // Execute the script
            connection.createStatement().execute(script);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

