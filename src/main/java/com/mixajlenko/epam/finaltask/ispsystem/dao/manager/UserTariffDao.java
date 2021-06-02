package com.mixajlenko.epam.finaltask.ispsystem.dao.manager;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserTariffDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.epam.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.model.UserTariff;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserTariffDao implements IUserTariffDao {

    @Override
    public UserTariff getById(Integer id) throws SQLException, NamingException {
        UserTariff userTariff = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF_BY_ID.getConstant())) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userTariff = new UserTariff();
                    userTariff.setId(id);
                    userTariff.setUserId(resultSet.getInt(2));
                    userTariff.setTariffId(resultSet.getInt(3));
                    userTariff.setSubDate(resultSet.getString(4));
                    userTariff.setStatus(resultSet.getInt(5));
                    userTariff.setNextBill(resultSet.getString(6));
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
    public List<UserTariff> getAll() throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        List<UserTariff> userTariffs = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF.getConstant())) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UserTariff userTariff = new UserTariff();
                    userTariff.setId(resultSet.getInt(1));
                    userTariff.setUserId(resultSet.getInt(2));
                    userTariff.setTariffId(resultSet.getInt(3));
                    userTariff.setSubDate(resultSet.getString(4));
                    userTariff.setStatus(resultSet.getInt(5));
                    userTariff.setNextBill(resultSet.getString(6));
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
    public boolean update(UserTariff entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_USER_TARIFF.getConstant())) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getTariffId());
            statement.setString(3, entity.getSubDate());
            statement.setInt(4, entity.getStatus());
            statement.setString(5, entity.getNextBill());
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
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER_PLAN.getConstant())) {
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
    public boolean add(UserTariff entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_USERS_PLAN.getConstant())) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getTariffId());
            statement.setString(3, CommandUtil.getDate());
            statement.setInt(4, 0);
            statement.setString(5, "didn't paid");
            statement.executeUpdate();
            connection.commit();
        } finally {
            connection.close();
        }
        return true;
    }

    @Override
    public List<UserTariff> getAllUserTariffInfoByUserId(int userId) throws NamingException, SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        List<UserTariff> userTariffs = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF.getConstant())) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt(2) == userId) {
                        UserTariff userTariff = new UserTariff();
                        userTariff.setId(resultSet.getInt(1));
                        userTariff.setUserId(resultSet.getInt(2));
                        userTariff.setTariffId(resultSet.getInt(3));
                        userTariff.setSubDate(resultSet.getString(4));
                        userTariff.setStatus(resultSet.getInt(5));
                        userTariff.setNextBill(resultSet.getString(6));
                        userTariffs.add(userTariff);
                    }
                }
            }
        } catch (SQLException e) {
            return Collections.emptyList();
        } finally {
            connection.close();
        }
        return userTariffs;
    }

//    @Override
//    public List<Tariff> getUserTariffList(int userId) throws NamingException, SQLException {
//        Connection connection = ConnectionFactory.getInstance().getConnection();
//        List<Tariff> userTariffs = new ArrayList<>();
//        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_USER_TARIFF.getConstant())) {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    Tariff tariff = new Tariff();
//                    tariff.setId(resultSet.getInt(1));
//                    tariff.setName(resultSet.getString(2));
//                    tariff.setDescription(resultSet.getString(3));
//                    tariff.setPrice(resultSet.getInt(4));
//                    userTariffs.add(tariff);
//                }
//            }
//        } catch (SQLException e) {
//            return Collections.emptyList();
//        } finally {
//            connection.close();
//        }
//        return userTariffs;
//    }

    @Override
    public UserTariff getUserTariffByUserId(int userId) throws NamingException, SQLException {
        UserTariff userTariff = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SqlQueries.ALL_USER_TARIFF_BY_USER_ID.getConstant())) {
                while (rs.next()) {
                    if (rs.getInt(1) == userId) {
                        userTariff = new UserTariff();
                        userTariff.setId(rs.getInt(1));
                        userTariff.setUserId(rs.getInt(2));
                        userTariff.setTariffId(rs.getInt(3));
                        userTariff.setSubDate(rs.getString(4));
                        userTariff.setStatus(rs.getInt(5));
                        userTariff.setNextBill(rs.getString(6));
                    }
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
                    userTariff.setSubDate(resultSet.getString(4));
                    userTariff.setStatus(resultSet.getInt(5));
                    userTariff.setNextBill(resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return userTariff;
    }
}
