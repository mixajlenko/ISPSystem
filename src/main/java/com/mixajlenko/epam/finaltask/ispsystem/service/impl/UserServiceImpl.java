package com.mixajlenko.epam.finaltask.ispsystem.service.impl;

import com.mixajlenko.epam.finaltask.ispsystem.dao.IAccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory;

import com.mixajlenko.epam.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.UserService;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IUserDao userDao = daoFactory.getUserDao();
    private IAccountDao accountDao = daoFactory.getAccountDao();


    @Override
    public List<User> getAll() throws SQLException, NamingException {
        try {
            return userDao.getAll();
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
    public boolean setUserService(User user, int servicePlanId) throws SQLException, NamingException {
        try {
            return userDao.setUserService(user, servicePlanId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Service> getUserService(int userId) throws SQLException, NamingException {
        try {
            return userDao.getUserService(userId);
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
        Account account = accountDao.getUserId(user.getId());
        if (account.getUserId() != user.getId() && !account.getPassword().equals(password)) {
            throw new NotFoundUserException();
        }
        return user;
    }
}
