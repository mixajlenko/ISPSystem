package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.finaltask.ispsystem.dao.IUserTariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserTariffService implements IUserTariffService {

    private static Logger logger = Logger.getLogger(UserTariffService.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IUserTariffDao userTariffDao = daoFactory.getUserTariffDao();
    private ITariffDao tariffDao = daoFactory.getTariffDao();
    private IUserDao userDao = daoFactory.getUserDao();

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
            for (UserTariff userTariff : userTariffDao.getAllUserTariffInfoByUserId(userId)) {
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
            return userTariffDao.getUserTariffByTariffIdUserId(tariffId, userId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteByUseIdTariffId(int userId, int tariffId) throws NamingException, SQLException {
        try {
            return userTariffDao.deleteByUseIdTariffId(userId, tariffId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkForMonthPayment(int userId) throws SQLException, NamingException {
        List<UserTariff> list = userTariffDao.getAllUserTariffInfoByUserId(userId);
        list.stream().filter(o -> Objects.nonNull(o.getNextBill()))
                .filter(o -> CommandUtil.getDate().after(o.getNextBill()))
                .forEach(o -> {
                    try {
                        o.setStatus(0);
                        userTariffDao.update(o);
                        User user = userDao.getById(o.getUserId());
                        user.setStatus(0);
                        userDao.update(user);
                    } catch (SQLException | NamingException throwables) {
                        throwables.printStackTrace();
                    }
                });
        return true;
    }
}
