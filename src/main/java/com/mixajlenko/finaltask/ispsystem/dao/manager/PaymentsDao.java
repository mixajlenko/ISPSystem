package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.dao.IPaymentsDao;
import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.finaltask.ispsystem.model.Payment;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentsDao implements IPaymentsDao {
    /* TODO fix code duplicates */

    private static Logger logger = Logger.getLogger(PaymentsDao.class);

    @Override
    public Payment getById(Integer id) throws SQLException, NamingException {
        logger.info("getById with " + id + " argument start");
        Payment payment = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SqlQueries.ALL_PAYMENTS.getConstant())) {
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
        logger.info("getById with " + id + " success");
        return payment;
    }

    @Override
    public List<Payment> getAll() throws SQLException, NamingException {
        logger.info("getAll start");
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_PAYMENTS.getConstant());
             ResultSet resultSet = statement.executeQuery()) {
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
        } catch (SQLException e) {
            logger.info("getAll fail. Return empty list");
            return Collections.emptyList();
        }
        logger.info("getAll success");
        return payments;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, NamingException {
        logger.info("update " + entity + " start");
        Connection connection = ConnectionFactory.getInstance().getConnection();
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
            logger.info("update " + entity + " fail. Rollback connection");
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
        logger.info("update " + entity + " success");
        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        logger.info("delete " + id + " start");
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_PAYMENT.getConstant())) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.info("delete " + id + " fail. Rollback connection");
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
        logger.info("delete " + id + " success");
        return true;
    }

    @Override
    public boolean add(Payment entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
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
            connection.close();
        }
        return true;
    }

    @Override
    public List<Payment> getAllById(int id) throws NamingException, SQLException {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_PAYMENTS_BY_USER_ID.getConstant())) {
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
