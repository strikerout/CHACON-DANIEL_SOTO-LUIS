package com.backend.repository.impl;

import com.backend.entity.Domicilio;
import com.backend.repository.IDao;
import com.backend.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioDaoH2 implements IDao<Domicilio> {
    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

    @Override
    public Domicilio buscar(Long id) {
        final String query = "SELECT * FROM DOMICILIOS WHERE ID = ?";
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                domicilio = new Domicilio(resultset.getLong("id"), resultset.getString("calle"), resultset.getInt("numero"), resultset.getString("localidad"), resultset.getString("provincia"));
            }
            LOGGER.info("Domicilio obtenido exitosamente: " + domicilio.toString());
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se ha podido cerrar la conexión." + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        final String insert = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES(?, ?, ?, ?)";
        Connection connection = null;
        Domicilio domicilioGuardado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                domicilioGuardado = new Domicilio(resultSet.getLong("id"), domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(), domicilio.getProvincia());
            }

            connection.commit();
            LOGGER.info("Guardado con éxito: " + domicilioGuardado.toString());

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            exception.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.error("Ha ocurrido un problema, deshaciendo cambios. " + exception.getMessage());
                    exception.printStackTrace();
                } catch (SQLException sqlException) {
                    LOGGER.error(sqlException.getMessage());
                    sqlException.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                LOGGER.error("No se ha podido cerrar la conexión" + exception.getMessage());
            }
        }

        return domicilioGuardado;
    }

    @Override
    public List<Domicilio> listarTodos() {
        final String query = "SELECT * FROM DOMICILIOS";
        List<Domicilio> listaDomicilio = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Domicilio paciente = new Domicilio(resultset.getLong("id"), resultset.getString("calle"), resultset.getInt("numero"), resultset.getString("localidad"), resultset.getString("provincia"));

                listaDomicilio.add(paciente);
            }
            LOGGER.info("Domicilio obtenidos exitosamente: " + listaDomicilio);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se ha podido cerrar la conexión." + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return listaDomicilio;
    }
}
