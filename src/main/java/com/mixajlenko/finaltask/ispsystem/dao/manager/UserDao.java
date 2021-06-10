package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class UserDao implements IUserDao {
    /* TODO fix code duplicates */

    private static Logger logger = Logger.getLogger(UserDao.class);

    @Override
    public User getById(Integer id) throws NamingException, SQLException {
        User user = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection(); Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SqlQueries.ALL_USERS.getConstant())) {
            while (rs.next()) {
                if (rs.getInt(1) == id) {
                    user = new User();
                    user.setId(id);
                    user.setFirstName(rs.getString(2));
                    user.setSecondName(rs.getString(5));
                    user.setPhone(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    user.setRole(rs.getInt(9));
                    user.setWallet(rs.getInt(6));
                    user.setStatus(rs.getInt(7));
                    user.setPassword(rs.getString(8));
                }
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException, NamingException {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_USERS.getConstant());
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setSecondName(rs.getString(5));
                user.setPhone(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setRole(rs.getInt(9));
                user.setWallet(rs.getInt(6));
                user.setStatus(rs.getInt(7));
                user.setPassword(rs.getString(8));
                users.add(user);
            }
        } finally {
            connection.close();
        }
        return users;
    }

    @Override
    public boolean update(User entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_USER.getConstant())) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getSecondName());
            statement.setString(3, entity.getPhone());
            statement.setString(4, entity.getEmail());
            statement.setInt(5, entity.getWallet());
            statement.setInt(6, entity.getRole());
            statement.setInt(7, entity.getStatus());
            statement.setString(8, entity.getPassword());
            statement.setInt(9, entity.getId());
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
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER.getConstant())) {
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
    public boolean add(User entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_USER.getConstant())) {
            statement.setString(1, entity.getFirstName());
            statement.setString(4, entity.getSecondName());
            statement.setString(2, entity.getPhone());
            statement.setString(3, entity.getEmail());
            statement.setInt(5, entity.getWallet());
            statement.setInt(6, entity.getStatus());
            statement.setString(7, entity.getPassword());
            statement.setInt(8, entity.getRole());
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
    public User getUserByName(String name) throws NamingException, SQLException {
        User user = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_USER_BY_NAME.getConstant())) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setSecondName(resultSet.getString(5));
                user.setPhone(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole(resultSet.getInt(9));
                user.setWallet(resultSet.getInt(6));
                user.setStatus(resultSet.getInt(7));
                user.setPassword(resultSet.getString(8));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws NamingException, SQLException {
        User user = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_USER_BY_EMAIL.getConstant())) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setSecondName(resultSet.getString(5));
                    user.setPhone(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setRole(resultSet.getInt(9));
                    user.setWallet(resultSet.getInt(6));
                    user.setStatus(resultSet.getInt(7));
                    user.setPassword(resultSet.getString(8));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }
}
