package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.dao.IUserTariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mixajlenko.finaltask.ispsystem.dao.manager.crudconstants.Constants.*;

public class UserTariffDao implements IUserTariffDao {

    private static final Logger logger = Logger.getLogger(UserTariffDao.class);

    @Override
    public UserTariff getById(Integer id) throws SQLException, NamingException {
        logger.info("getById with " + id + " argument start");
        UserTariff userTariff;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF_BY_ID.getConstant())) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            userTariff = initUserTariffList(resultSet).get(0);
        }
        logger.info("getById with " + id + SUCCESS);
        return userTariff;
    }

    @Override
    public List<UserTariff> getAll() throws SQLException, NamingException {
        logger.info("getAll" + START);
        List<UserTariff> userTariffs;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF.getConstant());
             var resultSet = statement.executeQuery()) {
            userTariffs = initUserTariffList(resultSet);
        } catch (SQLException e) {
            logger.info("getAll fail. Return empty list");
            return Collections.emptyList();
        }
        logger.info("getAll" + SUCCESS);
        return userTariffs;
    }

    @Override
    public boolean update(UserTariff entity) throws SQLException, NamingException {
        logger.info(UPDATE + entity + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.UPDATE_USER_TARIFF.getConstant())) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getTariffId());
            statement.setDate(3, entity.getSubDate());
            statement.setInt(4, entity.getStatus());
            statement.setDate(5, entity.getNextBill());
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

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        logger.info(DELETE + id + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER_PLAN.getConstant())) {
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
        return true;
    }

    @Override
    public boolean add(UserTariff entity) throws SQLException, NamingException {
        logger.info(ADD + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.INSERT_USERS_PLAN.getConstant())) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getTariffId());
            statement.setInt(3, 0);
            statement.setDate(4, entity.getNextBill());
            statement.setDate(5, CommandUtil.getDate());
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
        return true;
    }

    /**
     * Execute SELECT query.
     * Get all rows from USERS_PLAN table which user_id is @param
     *
     * @param userId - primary key of USER table.
     * @return - List of UserTariff objects which were found in UserTariff table.
     * @throws NamingException - if troubles with connection.
     * @throws SQLException    - if query is incorrect or connection is failed.
     */
    @Override
    public List<UserTariff> getUserTariffByUserId(int userId) throws NamingException, SQLException {
        List<UserTariff> userTariffs;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.USER_TARIFF_BY_USER_ID.getConstant())) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            userTariffs = initUserTariffList(rs);
        }
        return userTariffs;
    }

    /**
     * Execute SELECT query.
     * Get row from USERS_PLAN table which user_id and tariff_id same with params
     *
     * @param userId   - primary key of USER table.
     * @param tariffId - primary key of TARIFF table.
     * @return - object of UserTariff which was generated from found info.
     * @throws NamingException - if troubles with connection.
     * @throws SQLException    - if query is incorrect or connection is failed.
     */
    @Override
    public UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException {
        logger.info("getUserTariffByTariffIdUserId " + START);
        UserTariff userTariff;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF_BY_TARIFF_ID_USER_ID.getConstant())) {

            statement.setInt(1, tariffId);
            statement.setInt(2, userId);
            var resultSet = statement.executeQuery();
            userTariff = initUserTariffList(resultSet).get(0);

        }
        logger.info("getUserTariffByTariffIdUserId " + SUCCESS);
        return userTariff;

    }

    /**
     * Execute DELETE query in USERS_PLAN table row with user_id and tariff_id (primary keys).
     * As result we have USERS_PLAN table without row which primary kay the same with params
     * Rollback connection if row with  such @param is not exist.
     *
     * @param userId   - USER table primary key.
     * @param tariffId - TARIFF table primary key.
     * @return - true if delete is successfully and false if not.
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public boolean deleteByUserIdTariffId(int userId, int tariffId) throws NamingException, SQLException {
        logger.info(DELETE + "with userId: " + userId + " and tariffId: " + tariffId + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER_PLAN_BY_USER_ID_TARIFF_ID.getConstant())) {
            statement.setInt(1, userId);
            statement.setInt(2, tariffId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.info(DELETE + ROLLBACK);
            connection.rollback();
            return false;
        } finally {
            logger.info(CLOSE_CONNECTION);
            connection.close();
        }
        logger.info(DELETE + "with userId: " + userId + " and tariffId: " + tariffId + SUCCESS);
        return true;
    }

    private List<UserTariff> initUserTariffList(ResultSet resultSet) throws SQLException {
        List<UserTariff> userTariffs = new ArrayList<>();
        while (resultSet.next()) {
            var userTariff = new UserTariff();
            userTariff.setId(resultSet.getInt(1));
            userTariff.setUserId(resultSet.getInt(2));
            userTariff.setTariffId(resultSet.getInt(3));
            userTariff.setStatus(resultSet.getInt(4));
            userTariff.setNextBill(resultSet.getDate(5));
            userTariff.setSubDate(resultSet.getDate(6));
            userTariffs.add(userTariff);
        }
        return userTariffs;
    }
}
