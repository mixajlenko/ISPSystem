package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import static com.mixajlenko.finaltask.ispsystem.dao.manager.crudconstants.Constants.*;

public class UserDao implements IUserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class);


    @Override
    public User getById(Integer id) throws NamingException, SQLException {
        logger.info("getById with " + id + " argument start");
        User user;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.USERS_BY_ID.getConstant())) {

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            user = initUserList(resultSet).get(0);

        }
        logger.info("getById with " + id + SUCCESS);
        return user;

    }

    @Override
    public List<User> getAll() throws SQLException, NamingException {
        logger.info("getAll" + START);
        List<User> users;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(SqlQueries.ALL_USERS.getConstant())) {
            users = initUserList(rs);
        } catch (SQLException e) {
            logger.info("getAll fail. Return empty list");
            return Collections.emptyList();

        }
        logger.info("getAll" + SUCCESS);
        return users;

    }

    @Override
    public boolean update(User entity) throws SQLException, NamingException {
        logger.info(UPDATE + entity + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.UPDATE_USER.getConstant())) {
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
        try (var statement = connection.prepareStatement(SqlQueries.DELETE_FROM_USER.getConstant())) {
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

    @Override
    public boolean add(User entity) throws SQLException, NamingException {
        logger.info(ADD + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.INSERT_USER.getConstant())) {
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

    @Override
    public User getUserByName(String name) throws NamingException {
        logger.info("getUserByName " + name + START);
        User user;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.SELECT_USER_BY_NAME.getConstant())) {

            statement.setString(1, name);

            var resultSet = statement.executeQuery();

            user = initUserList(resultSet).get(0);

        } catch (SQLException e) {
            logger.info("getByName " + name + " Fail");
            throw new NotFoundUserException();
        }
        logger.info("getByName " + name + SUCCESS);
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws NamingException, SQLException {
        logger.info("getUserByEmail " + email + START);
        User user = null;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.SELECT_USER_BY_EMAIL.getConstant())) {
            statement.setString(1, email);
            var rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
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
        logger.info("getUserByEmail " + email + SUCCESS);
        return user;
    }

    private List<User> initUserList(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            var user = new User();
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
        return users;
    }
}
