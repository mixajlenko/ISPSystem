package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.dao.IPaymentsDao;
import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.finaltask.ispsystem.model.Payment;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentsDao implements IPaymentsDao {

    @Override
    public Payment getById(Integer id) throws SQLException, NamingException {
        Payment payment = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SqlQueries.ALL_PAYMENTS.getConstant())) {
                while (rs.next()) {
                    if (rs.getInt(1) == id) {
                        payment = new Payment();
                        payment.setId(id);
                        payment.setUserId(rs.getInt(2));
                        payment.setBill(rs.getInt(3));
                        payment.setStatus(rs.getInt(4));
                        payment.setBalance(rs.getInt(5));
                        payment.setType(rs.getString(6));
                        payment.setDate(rs.getDate(7));
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return payment;
    }

    @Override
    public List<Payment> getAll() throws SQLException, NamingException {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_PAYMENTS.getConstant())) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = new Payment();
                    payment.setId(resultSet.getInt(1));
                    payment.setUserId(resultSet.getInt(2));
                    payment.setBill(resultSet.getInt(3));
                    payment.setStatus(resultSet.getInt(4));
                    payment.setBalance(resultSet.getInt(5));
                    payment.setType(resultSet.getString(6));
                    payment.setDate(resultSet.getDate(7));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            return Collections.emptyList();
        }
        return payments;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_PAYMENT.getConstant())) {
            statement.setInt(1, entity.getBill());
            statement.setInt(2, entity.getStatus());
            statement.setInt(3, entity.getBalance());
            statement.setString(4, entity.getType());
            statement.setDate(5, entity.getDate());
            statement.setInt(6, entity.getId());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_PAYMENT.getConstant())) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return true;
    }

    @Override
    public boolean add(Payment entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_PAYMENT.getConstant())) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getBill());
            statement.setInt(3, entity.getStatus());
            statement.setInt(4, entity.getBalance());
            statement.setDate(5, entity.getDate());
            statement.setString(6, entity.getType());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return true;
    }

    @Override
    public List<Payment> getAllById(int id) throws NamingException, SQLException {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_PAYMENTS_BY_USER_ID.getConstant())) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Payment payment = new Payment();
                while (resultSet.next()) {
                    if (resultSet.getInt(2) == id) {
                        payment.setId(resultSet.getInt(1));
                        payment.setUserId(id);
                        payment.setBill(resultSet.getInt(3));
                        payment.setStatus(resultSet.getInt(4));
                        payment.setBalance(resultSet.getInt(5));
                        payment.setType(resultSet.getString(6));
                        payment.setDate(resultSet.getDate(7));
                        payments.add(payment);
                    }
                }
            }
        } catch (SQLException e) {
            return Collections.emptyList();
        }
        return payments;
    }
}
