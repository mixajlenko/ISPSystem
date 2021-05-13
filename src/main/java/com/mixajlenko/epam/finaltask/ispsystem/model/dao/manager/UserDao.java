package com.mixajlenko.epam.finaltask.ispsystem.model.dao.manager;

import com.mixajlenko.epam.finaltask.ispsystem.model.dao.entity.User;
import com.mixajlenko.epam.finaltask.ispsystem.model.dao.factory.ConnectionSupport;
import com.mixajlenko.epam.finaltask.ispsystem.model.dao.factory.DaoFactory;
import com.mixajlenko.epam.finaltask.ispsystem.model.dao.queries.SqlQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends ConnectionSupport implements IEntityDAO<User> {

    public UserDao(DaoFactory dataSource) {
        super(dataSource);
    }

    @Override
    public User getById(int id) throws SQLException {
        User user = null;
        Connection connection = dataSource.getConnection();
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
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = dataSource.getConnection();
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
    public boolean updateEntity(User entity) throws SQLException {
        Connection connection = dataSource.getConnection();
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
    public boolean deleteEntity(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
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
    public boolean insertEntity(User entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_USER.getConstant());
             ResultSet ids = connection.createStatement().executeQuery(SqlQueries.COUNT_USER_ROWS.getConstant())) {
            int maxId = 1;
            while (ids.next()) {
                maxId = ids.getInt(1);
            }
            for (int i = 1; i < maxId; i++) {
                if (getById(i) == null) {
                    entity.setId(i);
                    break;
                }
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
}
