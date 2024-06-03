package com.backend.repository.impl;

import com.backend.entity.Domicilio;
import com.backend.entity.Paciente;
import com.backend.repository.IDao;
import com.backend.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteDaoH2 implements IDao<Paciente> {
    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);

    @Autowired
    private DomicilioDaoH2 domicilioDaoH2;

    @Override
    public Paciente buscar(Long id) {
        final String query = "SELECT * FROM PACIENTES WHERE ID = ?";
        Connection connection = null;
        Paciente paciente = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Domicilio domicilio = domicilioDaoH2.buscar(resultset.getLong("domicilio"));
                paciente = new Paciente(resultset.getLong("id"), resultset.getLong("dni"), resultset.getString("nombre"), resultset.getString("apellido"), domicilio, resultset.getDate("fecha_alta").toLocalDate());
            }
            LOGGER.info("Paciente obtenido exitosamente: " + paciente.toString());
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
        return paciente;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        final String insert = "INSERT INTO PACIENTES(DNI, NOMBRE, APELLIDO, DOMICILIO, FECHA_ALTA) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        Paciente pacienteGuardado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            domicilioDaoH2 = new DomicilioDaoH2();
            Domicilio domicilioRegistrado = domicilioDaoH2.guardar(paciente.getDomicilio());

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, paciente.getDni());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getApellido());
            preparedStatement.setLong(4, domicilioRegistrado.getId());
            preparedStatement.setDate(5, Date.valueOf(paciente.getFechaAlta()));

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            Domicilio domicilio = domicilioDaoH2.buscar(domicilioRegistrado.getId());

            if (domicilio == null) {
                throw new Exception("Domicilio no encontrado");
            }

            //TODO: PREGUNTAR A LA PROFE SI REQUERIMOS QUE EXISTA EL DOMICILIO ANTES DEL PACIENTE; O SI CREAMOS EL DOMICILIO EN BASE AL QUE SE LE PROVEE AL PACIENTE

            while (resultSet.next()) {
                pacienteGuardado = new Paciente(resultSet.getLong("id"), paciente.getDni(), paciente.getNombre(), paciente.getApellido(), domicilio, paciente.getFechaAlta());
            }

            connection.commit();
            LOGGER.info("Guardado con éxito: " + pacienteGuardado.toString());

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
        return pacienteGuardado;
    }

    @Override
    public List<Paciente> listarTodos() {
        final String query = "SELECT * FROM PACIENTES";
        List<Paciente> listaPacientes = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Domicilio domicilio = domicilioDaoH2.buscar(resultset.getLong("domicilio"));

                Paciente paciente = new Paciente(resultset.getLong("id"), resultset.getLong("dni"), resultset.getString("nombre"), resultset.getString("apellido"), domicilio, resultset.getDate("fecha_alta").toLocalDate());

                listaPacientes.add(paciente);
            }
            LOGGER.info("Pacientes obtenidos exitosamente: " + listaPacientes);
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
        return listaPacientes;
    }
}
