package com.mixajlenko.epam.finaltask.ispsystem.service.impl;

import com.mixajlenko.epam.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserTariffDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.epam.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserTariffService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTariffService implements IUserTariffService {

    private static Logger logger = Logger.getLogger(UserTariffService.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IUserTariffDao userTariffDao = daoFactory.getUserTariffDao();
    private ITariffDao tariffDao = daoFactory.getTariffDao();

    @Override
    public List<UserTariff> getAll() throws SQLException, NamingException {
        try {
            return userTariffDao.getAll();
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserTariff getById(Integer id) throws SQLException, NamingException {
        try {
            return userTariffDao.getById(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean add(UserTariff entity) throws SQLException, NamingException {
        try {
            return userTariffDao.add(entity);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserTariff update(UserTariff entity) throws SQLException, NamingException {
        try {
            userTariffDao.update(entity);
            return entity;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        try {
            return userTariffDao.delete(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserTariff> getAllUserTariffByUserId(int userId) throws NamingException, SQLException {
        try {
            return userTariffDao.getAllUserTariffInfoByUserId(userId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tariff> getUserTariffList(int userId) throws NamingException, SQLException {
        try {
            List<Tariff> tariffs = new ArrayList<>();
            for (UserTariff userTariff : userTariffDao.getAllUserTariffInfoByUserId(userId)){
                tariffs.add(tariffDao.getById(userTariff.getTariffId()));
            }
            return tariffs;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserTariff getUserTariffByUserId(int userId) throws NamingException, SQLException {
        try {
            return userTariffDao.getUserTariffByUserId(userId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException {
        try {
            return userTariffDao.getUserTariffByTariffIdUserId(tariffId,userId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }
}
