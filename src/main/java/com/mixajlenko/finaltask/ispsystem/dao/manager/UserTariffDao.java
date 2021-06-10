package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.dao.IUserTariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;

import javax.naming.NamingException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserTariffDao implements IUserTariffDao {
    /* TODO fix code duplicates */

    @Override
    public UserTariff getById(Integer id) throws SQLException, NamingException {
        UserTariff userTariff = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF_BY_ID.getConstant())) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userTariff = new UserTariff();
                    userTariff.setId(id);
                    userTariff.setUserId(resultSet.getInt(2));
                    userTariff.setTariffId(resultSet.getInt(3));
                    userTariff.setSubDate(resultSet.getDate(4));
                    userTariff.setStatus(resultSet.getInt(5));
                    userTariff.setNextBill(resultSet.getDate(6));
                }
            }
        }
        return userTariff;
    }

    @Override
    public List<UserTariff> getAll() throws SQLException, NamingException {
        List<UserTariff> userTariffs = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF.getConstant());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserTariff userTariff = new UserTariff();
                userTariff.setId(resultSet.getInt(1));
                userTariff.setUserId(resultSet.getInt(2));
                userTariff.setTariffId(resultSet.getInt(3));
                userTariff.setStatus(resultSet.getInt(4));
                userTariff.setNextBill(resultSet.getDate(5));
                userTariff.setSubDate(resultSet.getDate(6));
                userTariffs.add(userTariff);
            }
        } catch (SQLException e) {
            return Collections.emptyList();
        }
        return userTariffs;
    }

    @Override
    public boolean update(UserTariff entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_USER_TARIFF.getConstant())) {
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
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER_PLAN.getConstant())) {
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
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_USERS_PLAN.getConstant())) {
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
    public List<UserTariff> getAllUserTariffInfoByUserId(int userId) throws NamingException, SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        List<UserTariff> userTariffs = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF.getConstant());
             ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt(2) == userId) {
                        UserTariff userTariff = new UserTariff();
                        userTariff.setId(resultSet.getInt(1));
                        userTariff.setUserId(resultSet.getInt(2));
                        userTariff.setTariffId(resultSet.getInt(3));
                        userTariff.setStatus(resultSet.getInt(4));
                        userTariff.setNextBill(resultSet.getDate(5));
                        userTariff.setSubDate(resultSet.getDate(6));
                        userTariffs.add(userTariff);
                    }
                }
        } catch (SQLException e) {
            return Collections.emptyList();
        } finally {
            connection.close();
        }
        return userTariffs;
    }

    @Override
    public UserTariff getUserTariffByUserId(int userId) throws NamingException, SQLException {
        UserTariff userTariff = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SqlQueries.ALL_USER_TARIFF_BY_USER_ID.getConstant())) {
            while (rs.next()) {
                if (rs.getInt(1) == userId) {
                    userTariff = new UserTariff();
                    userTariff.setId(rs.getInt(1));
                    userTariff.setUserId(rs.getInt(2));
                    userTariff.setTariffId(rs.getInt(3));
                    userTariff.setStatus(rs.getInt(4));
                    userTariff.setNextBill(rs.getDate(5));
                    userTariff.setSubDate(rs.getDate(6));
                }
            }
        }
        return userTariff;
    }

    @Override
    public UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException {
        UserTariff userTariff = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF_BY_TARIFF_ID_USER_ID.getConstant())) {
            statement.setInt(1, tariffId);
            statement.setInt(2, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userTariff = new UserTariff();
                    userTariff.setId(resultSet.getInt(1));
                    userTariff.setUserId(resultSet.getInt(2));
                    userTariff.setTariffId(resultSet.getInt(3));
                    userTariff.setStatus(resultSet.getInt(4));
                    userTariff.setNextBill(resultSet.getDate(5));
                    userTariff.setSubDate(resultSet.getDate(6));
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return userTariff;
    }

    @Override
    public boolean deleteByUseIdTariffId(int userId, int tariffId) throws NamingException, SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER_PLAN_BY_USER_ID_TARIFF_ID.getConstant())) {
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
}
