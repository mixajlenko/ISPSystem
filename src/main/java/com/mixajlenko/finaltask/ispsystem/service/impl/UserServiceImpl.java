package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.finaltask.ispsystem.dao.factory.DaoFactory;

import com.mixajlenko.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements IUserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

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
        } catch (DataBaseException e) {
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
    public User update(User entity) throws SQLException, NamingException {
        try {
            userDao.update(entity);
            return entity;
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
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException, NamingException {
        try {
            return userDao.getUserByEmail(email);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByLoginAndPass(String login, String password) throws SQLException, NamingException {
        User user = userDao.getUserByEmail(login);
        if (!user.getPassword().equals(password)) {
            throw new NotFoundUserException();
        }
        return user;
    }

    @Override
    public int blockedAccounts(List<User> users) {
        logger.info("get blockedAccounts");
        int result = 0;
        for (User user : users) {
            if (user.getStatus() == 0)
                result++;
        }
        return result;
    }
}
