package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo buscar(Long id) {
        final String query = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologo = new Odontologo(resultSet.getLong("id"), resultSet.getLong("numero_matricula"), resultSet.getString("nombre"), resultSet.getString("apellido"));
            }
            LOGGER.info("Odontologo obtenido exitosamente: " + odontologo.toString());
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
        return odontologo;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        final String insert = "INSERT INTO ODONTOLOGOS(NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?)";
        Connection connection = null;
        Odontologo odontologoGuardado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, odontologo.getNumMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                odontologoGuardado = new Odontologo(resultSet.getLong("id"), odontologo.getNumMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }

            connection.commit();
            LOGGER.info("Guardado con éxito: " + odontologoGuardado.toString());

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

        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        final String query = "SELECT * FROM ODONTOLOGOS";
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(resultSet.getLong("id"), resultSet.getLong("numero_matricula"), resultSet.getString("nombre"), resultSet.getString("apellido"));

                listaOdontologos.add(odontologo);
            }
            LOGGER.info("Odontólogos obtenidos exitosamente: " + listaOdontologos);

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
        return listaOdontologos;
    }
}
