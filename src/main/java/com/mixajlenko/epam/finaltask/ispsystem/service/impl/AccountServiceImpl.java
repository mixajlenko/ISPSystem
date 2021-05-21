package com.mixajlenko.epam.finaltask.ispsystem.service.impl;

import com.mixajlenko.epam.finaltask.ispsystem.dao.IAccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.epam.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.IAccountService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;

public class AccountServiceImpl implements IAccountService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IAccountDao accountDao = daoFactory.getAccountDao();

    @Override
    public Account getById(Integer id) throws SQLException, NamingException {
        Account account;
        try {
            account = accountDao.getById(id);
            logger.info("Account founded");
            return account;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean add(Account entity) throws SQLException, NamingException {
        try {
            accountDao.add(entity);
            logger.info("Account was added");
            return true;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Account update(Account entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Account getUserId(int id) throws SQLException, NamingException {
        Account account;
        try {
            account = accountDao.getUserId(id);
            logger.info("Account founded");
            return account;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }
}
