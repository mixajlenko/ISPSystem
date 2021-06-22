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

import static com.mixajlenko.finaltask.ispsystem.dao.manager.crudconstants.Constants.*;

public class PaymentsDao implements IPaymentsDao {

    private static final Logger logger = Logger.getLogger(PaymentsDao.class);

    /**
     * Execute SELECT query.
     * Search Payment info in database ISPManager by id (primary key of PAYMENT table).
     * Then put this data to java object and return as result.
     *
     * @param id The primary key of PAYMENT table
     * @return The payment object which was found by id
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public Payment getById(Integer id) throws SQLException, NamingException {
        logger.info("getById with " + id + " argument start");
        Payment payment;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SqlQueries.PAYMENTS_BY_ID.getConstant())) {

            preparedStatement.setInt(1, id);

            var rs = preparedStatement.executeQuery();

            payment = initPaymentList(rs).get(0);

        }
        logger.info("getById with " + id + SUCCESS);
        return payment;

    }

    /**
     * Execute SELECT query.
     * Get all rows from PAYMENT table in database ISPManager
     * and then put each to List and return as result.
     *
     * @return The List of payment objects.
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public List<Payment> getAll() throws SQLException, NamingException {
        logger.info("getAll" + START);
        List<Payment> payments;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.ALL_PAYMENTS.getConstant());
             var resultSet = statement.executeQuery()) {

            payments = initPaymentList(resultSet);

        } catch (SQLException e) {
            logger.info("getAll fail. Return empty list");
            return Collections.emptyList();
        }
        logger.info("getAll" + SUCCESS);
        return payments;

    }

    /**
     * Execute UPDATE query in PAYMENT table row with same id (primary kay) as entity object id.
     * As result updated row in PAYMENT table.
     * Rollback connection might be if object is incorrect.
     *
     * @param entity - java Payment object with new info.
     * @return - true if update was successfully and false if not.
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public boolean update(Payment entity) throws SQLException, NamingException {
        logger.info(UPDATE + entity + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.UPDATE_PAYMENT.getConstant())) {

            statement.setInt(1, entity.getBill());
            statement.setInt(2, entity.getStatus());
            statement.setInt(3, entity.getBalance());
            statement.setString(4, entity.getType());
            statement.setDate(5, entity.getDate());
            statement.setInt(6, entity.getId());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.info(UPDATE + entity + ROLLBACK);
            connection.rollback();
            return false;

        } finally {
            logger.info(CLOSE_CONNECTION);
            connection.close();
        }
        logger.info(UPDATE + entity + SUCCESS);
        return true;
    }

    /**
     * Execute DELETE query in PAYMENT table row with same id (primary kay) as @param.
     * As result we have PAYMENT table without row which  primary kay the same with @param
     * Rollback connection if row with  such @param is not exist.
     *
     * @param id - primary key of row in PAYMENT table.
     * @return true if delete is successfully and false if not.
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        logger.info(DELETE + id + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.DELETE_FROM_PAYMENT.getConstant())) {

            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.info(DELETE + id + ROLLBACK);
            connection.rollback();
            return false;

        } finally {
            logger.info(CLOSE_CONNECTION);
            connection.close();
        }
        logger.info(DELETE + id + SUCCESS);
        return true;

    }


    /**
     * Execute INSERT query in PAYMENT table row with same id (primary kay) as @param.
     * As result we have PAYMENT table without row which  primary kay the same with @param
     * Rollback connection if row with  such @param is not exist.
     *
     * @param entity - java object with info which will be add to PAYMENT table.
     * @return true if add was successfully and false if not.
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public boolean add(Payment entity) throws SQLException, NamingException {
        logger.info(ADD + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.INSERT_PAYMENT.getConstant())) {

            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getBill());
            statement.setInt(3, entity.getStatus());
            statement.setInt(4, entity.getBalance());
            statement.setDate(5, entity.getDate());
            statement.setString(6, entity.getType());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.info(ADD + ROLLBACK);
            connection.rollback();
            return false;

        } finally {
            logger.info(CLOSE_CONNECTION);
            connection.close();
        }
        logger.info(ADD + SUCCESS);
        return true;

    }

    /**
     * Execute SELECT query.
     * Get all rows from PAYMENT table in database ISPManager with user id @param
     * and then put each to List and return as result.
     *
     * @param id - user_id of PAYMENT table.
     * @return The List of payment objects by user id.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public List<Payment> getAllByUserId(int id) throws NamingException {
        logger.info("getAllById " + START);
        List<Payment> payments;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.GET_PAYMENTS_BY_USER_ID.getConstant())) {

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            payments = initPaymentList(resultSet);

        } catch (SQLException e) {
            logger.info("getAllById Fail");
            return Collections.emptyList();

        }
        logger.info("getAllById " + SUCCESS);
        return payments;

    }

    /**
     * Method initPaymentList generate Payment object from PAYMENT table with ResultSet
     * and then put all results to List.
     *
     * @param rs - result set with different query.
     * @return - List of Payment objects.
     * @throws SQLException - if query is incorrect or connection is failed.
     */
    private List<Payment> initPaymentList(ResultSet rs) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {

            var payment = new Payment();
            payment.setId(rs.getInt(1));
            payment.setUserId(rs.getInt(2));
            payment.setBill(rs.getInt(3));
            payment.setStatus(rs.getInt(4));
            payment.setBalance(rs.getInt(5));
            payment.setType(rs.getString(6));
            payment.setDate(rs.getDate(7));
            payments.add(payment);

        }
        return payments;

    }

}
