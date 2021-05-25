package com.mixajlenko.epam.finaltask.ispsystem.service.impl;

import com.mixajlenko.epam.finaltask.ispsystem.dao.IAccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.epam.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Account;

import com.mixajlenko.epam.finaltask.ispsystem.service.IAccountService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl implements IAccountService {

    private static Logger logger = Logger.getLogger(AccountServiceImpl.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IAccountDao accountDao = daoFactory.getAccountDao();

    @Override
    public List<Account> getAll() throws SQLException, NamingException {
        try {
            return accountDao.getAll();
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Account getById(Integer id) throws SQLException, NamingException {
        try {
            logger.info("Account founded");
            return accountDao.getById(id);
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
    public Account update(Account entity) throws SQLException, NamingException {
        try {
            accountDao.update(entity);
            return entity;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        try {
            accountDao.delete(id);
            return true;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Account getUserId(int id) throws SQLException, NamingException {
        try {
            logger.info("Account founded");
            return accountDao.getUserId(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int blockedAccounts(List<Account> accounts) {
        logger.info("get blockedAccounts");
        int result = 0;
        for (Account account : accounts) {
            if (account.getStatus() == 0)
                result++;
        }
        return result;
    }
}
