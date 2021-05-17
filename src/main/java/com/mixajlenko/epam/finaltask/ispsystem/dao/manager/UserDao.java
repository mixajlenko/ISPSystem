package com.mixajlenko.epam.finaltask.ispsystem.dao.manager;

import com.mixajlenko.epam.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.epam.finaltask.ispsystem.exception.DAOException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.dao.queries.SqlQueries;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements IUserDao {

    @Override
    public User getById(Integer id) throws SQLException, NamingException {
        User user = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SqlQueries.ALL_USERS.getConstant())) {
                while (rs.next()) {
                    if (rs.getInt(1) == id) {
                        user = new User();
                        user.setId(id);
                        user.setName(rs.getString(2));
                        user.setPhone(rs.getString(3));
                        user.setEmail(rs.getString(4));
                        user.setRole_id(rs.getInt(5));
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException, NamingException {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_USERS.getConstant())) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setPhone(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    user.setRole_id(rs.getInt(5));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return users;
    }

    @Override
    public boolean update(User entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_USER.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPhone());
            statement.setString(3, entity.getEmail());
            statement.setInt(4, entity.getRole_id());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER.getConstant())) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean add(User entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_USER.getConstant());
             ResultSet ids = connection.createStatement().executeQuery(SqlQueries.COUNT_USER_ROWS.getConstant())) {
            int maxId = 1;
            int ddd = 0;
            while (ids.next()) {
                maxId = ids.getInt(1);
            }
            for (int i = 1; i <= maxId; i++) {
                if (getById(i) == null) {
                    entity.setId(i);
                    break;
                } else {
                    ddd = i;
                }
            }
            if (ddd == maxId) {
                entity.setId(maxId + 1);
            }
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getPhone());
            statement.setString(4, entity.getEmail());
            statement.setInt(5, entity.getRole_id());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean setUserService(User user, int servicePlanId) throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        int id;
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_USERS_PLAN.getConstant())) {
            try (ResultSet resultSet = connection.createStatement().executeQuery(SqlQueries.COUNT_USERS_PLAN_ROWS.getConstant())) {
                resultSet.next();
                id = resultSet.getInt(1) + 1;
            }
            statement.setInt(1,id);
            statement.setInt(2, getById(user.getId()).getId());
            statement.setInt(3, servicePlanId);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
            connection.commit();
        } catch (NamingException e) {
            Logger.getLogger(TariffDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
            return false;
        }
        return true;
    }

    @Override
    public List<Service> getUserService(int userId) {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        List<Service> services = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_SERVICE_TARIFF.getConstant())) {
            try (ResultSet resultSet = statement.executeQuery()) {
                Service service = new Service();
                while (resultSet.next()) {
                    if (resultSet.getInt(2) == userId) {
                        service.setId(resultSet.getInt(1));
                        service.setName(resultSet.getString(2));
                        service.setDescription(resultSet.getString(3));
                        services.add(service);
                    }
                }
            }
        } catch (SQLException e) {
            return Collections.emptyList();
        }
        return services;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        int id;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_USER_BY_NAME.getConstant())) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setPhone(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole_id(resultSet.getInt(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_USER_BY_EMAIL.getConstant())) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setPhone(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole_id(resultSet.getInt(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

//    @Override
//    public List<User> getUserByStatus(int statusId) {
//        List<User> users = new ArrayList<>();
//        User user;
//        Connection connection = ConnectionFactory.getInstance().getConnection();
//        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_USER_BY_STATUS.getConstant())) {
//            statement.setInt(1, statusId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                resultSet.next();
//                user = new User();
//                user.setId(resultSet.getInt(1));
//                user.setName(resultSet.getString(2));
//                user.setPhone(resultSet.getString(3));
//                user.setEmail(resultSet.getString(4));
//                user.setRole_id(resultSet.getInt(5));
//                users.add(user);
//            }
//        } catch (SQLException throwables) {
//            return Collections.emptyList();
//        }
//        return users;
//    }
}
