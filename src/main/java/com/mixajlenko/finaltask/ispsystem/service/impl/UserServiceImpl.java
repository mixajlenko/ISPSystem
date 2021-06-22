package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.finaltask.ispsystem.dao.factory.DaoFactory;

import com.mixajlenko.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements IUserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IUserDao userDao = daoFactory.getUserDao();


    @Override
    public List<User> getAll() throws SQLException, NamingException {
        try {
            return userDao.getAll().stream()
                    .filter(p -> p.getRole() == 1)
                    .collect(Collectors.toList());
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(Integer id) throws SQLException, NamingException {
        try {
            return userDao.getById(id);
        } catch (DataBaseException | NotFoundServiceIdException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean add(User entity) throws SQLException, NamingException {
        try {
            return userDao.add(entity);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(User entity) throws SQLException, NamingException {
        try {
            return userDao.update(entity);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        try {
            return userDao.delete(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws SQLException, NamingException {
        try {
            return userDao.getUserByEmail(email);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Execute SELECT query.
     * Search User info in database ISPManager by login and password.
     * Then generate java object and return as result.
     *
     * @param login    - login column name in USER table
     * @param password - password column name in USER table.
     * @return - User object which generated from found info
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public User getByLoginAndPass(String login, String password) throws SQLException, NamingException {

        var user = userDao.getUserByEmail(login);
        if (Objects.isNull(user)) {
            logger.info("LOGIN");
            throw new NotFoundUserException();
        }

        if (!password.equals(user.getPassword())) {
            logger.info("PASSWORD");
            throw new NotFoundUserException();
        }
        return user;
    }

    /**
     * Counting how many user have status 0 (blocked)
     *
     * @param users - list of User objects.
     * @return count of users with blocked status.
     */
    @Override
    public int blockedAccounts(List<User> users) {
        logger.info("get blockedAccounts");
        var result = 0;
        for (User user : users) {
            if (user.getStatus() == 0)
                result++;
        }
        return result;
    }
}
