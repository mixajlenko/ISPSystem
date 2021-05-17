package com.mixajlenko.epam.finaltask.ispsystem.dao.manager;

import com.mixajlenko.epam.finaltask.ispsystem.connection.ConnectionFactory;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.dao.queries.SqlQueries;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
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
            statement.setInt(4, entity.getId());
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
    public boolean setUserService(int userId, int servicePlanId) {
        return false;
    }

    @Override
    public List<Service> getUserService(int userId) {
        return null;
    }
}
