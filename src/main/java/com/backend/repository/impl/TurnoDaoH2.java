package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.repository.IDao;
import com.backend.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {
    private static final Logger LOGGER = Logger.getLogger(TurnoDaoH2.class);
    private OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
    private PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();

    @Override
    public Turno buscar(Long id) {
        final String query = "SELECT * FROM TURNOS WHERE ID = ?";
        Connection connection = null;
        Turno turno = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Odontologo odontologo = odontologoDaoH2.buscar(resultset.getLong("odontologo"));
                Paciente paciente = pacienteDaoH2.buscar(resultset.getLong("paciente"));

                turno = new Turno(resultset.getLong("id"), resultset.getTimestamp("fecha_y_hora").toLocalDateTime(), odontologo, paciente);

            }
            LOGGER.info("Turno obtenido exitosamente: " + turno.toString());
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
        return turno;
    }

    @Override
    public Turno guardar(Turno turno) {
        final String insert = "INSERT INTO TURNOS (FECHA_Y_HORA, ODONTOLOGO, PACIENTE) VALUES (?, ?, ?)";
        Connection connection = null;
        Turno turnoGuardado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(turno.getFechaYHora()));
            preparedStatement.setLong(2, turno.getOdontologo().getId());
            preparedStatement.setLong(3, turno.getPaciente().getId());


            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            Odontologo odontologo = odontologoDaoH2.buscar(turno.getOdontologo().getId());

            if (odontologo == null) {
                throw new Exception("Odontologo no encontrado");
            }

            Paciente paciente = pacienteDaoH2.buscar(turno.getPaciente().getId());

            if (paciente == null) {
                throw new Exception("Paciente no encontrado");
            }

            while (resultSet.next()) {
                turnoGuardado = new Turno(resultSet.getLong("id"), turno.getFechaYHora(), odontologo, paciente);
            }

            connection.commit();
            LOGGER.info("Guardado con éxito: " + turnoGuardado.toString());

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
        return turnoGuardado;
    }

    @Override
    public List<Turno> listarTodos() {
        final String query = "SELECT * FROM TURNOS";
        List<Turno> listaTurnos = new ArrayList<>();
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Odontologo odontologo = odontologoDaoH2.buscar(resultset.getLong("odontologo"));
                Paciente paciente = pacienteDaoH2.buscar(resultset.getLong("paciente"));

                Turno turno = new Turno(resultset.getLong("id"), resultset.getTimestamp("fecha_y_hora").toLocalDateTime(), odontologo, paciente);

                listaTurnos.add(turno);
            }
            LOGGER.info("Turnos obtenidos exitosamente: " + listaTurnos);
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
        return listaTurnos;
    }
}
