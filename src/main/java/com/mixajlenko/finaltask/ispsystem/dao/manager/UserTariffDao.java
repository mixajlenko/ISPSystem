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

public class UserTariffDao implements IUserTariffDao {

    private static final Logger logger = Logger.getLogger(UserTariffDao.class);

    @Override
    public UserTariff getById(Integer id) throws SQLException, NamingException {
        List<UserTariff> userTariffs;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF_BY_ID.getConstant())) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            userTariffs = initUserTariffList(resultSet);
        }
        return userTariffs.get(0);
    }

    @Override
    public List<UserTariff> getAll() throws SQLException, NamingException {
        List<UserTariff> userTariffs;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF.getConstant());
             var resultSet = statement.executeQuery()) {
            userTariffs = initUserTariffList(resultSet);
        } catch (SQLException e) {
            return Collections.emptyList();
        }
        return userTariffs;
    }

    @Override
    public boolean update(UserTariff entity) throws SQLException, NamingException {
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
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER_PLAN.getConstant())) {
            statement.setInt(1, id);
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
    public boolean add(UserTariff entity) throws SQLException, NamingException {
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.INSERT_USERS_PLAN.getConstant())) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getTariffId());
            statement.setInt(3, 0);
            statement.setDate(4, entity.getNextBill());
            statement.setDate(5, CommandUtil.getDate());
            statement.executeUpdate();
            connection.commit();
        }
        return true;
    }

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

    @Override
    public UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException {
        UserTariff userTariff = null;
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF_BY_TARIFF_ID_USER_ID.getConstant())) {
            statement.setInt(1, tariffId);
            statement.setInt(2, userId);
            var resultSet = statement.executeQuery();
            userTariff = initUserTariffList(resultSet).get(0);
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return userTariff;
    }

    @Override
    public boolean deleteByUseIdTariffId(int userId, int tariffId) throws NamingException, SQLException {
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER_PLAN_BY_USER_ID_TARIFF_ID.getConstant())) {
            statement.setInt(1, userId);
            statement.setInt(2, tariffId);
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
