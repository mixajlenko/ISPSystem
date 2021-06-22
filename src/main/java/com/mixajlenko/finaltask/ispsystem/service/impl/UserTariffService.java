package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.finaltask.ispsystem.dao.IUserTariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserTariffService implements IUserTariffService {


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
        } catch (DataBaseException | NotFoundServiceIdException e) {
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
    public boolean update(UserTariff entity) throws SQLException, NamingException {
        try {
            return userTariffDao.update(entity);
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

    /**
     * Execute SELECT query.
     * Generate list with Tariff (all info about tariff) objects which have user
     *
     * @param userId - primary key of USER table
     * @return - List of Tariff objects
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public List<Tariff> getUserTariffList(int userId) throws NamingException, SQLException {
        try {
            List<Tariff> tariffs = new ArrayList<>();
            for (UserTariff userTariff : userTariffDao.getUserTariffByUserId(userId)) {
                tariffs.add(tariffDao.getById(userTariff.getTariffId()));
            }
            return tariffs;
        } catch (DataBaseException | NotFoundServiceIdException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Execute SELECT query.
     * Generate list with UserTariff (tariffs id's) objects which have user
     *
     * @param userId - primary key of USER table
     * @return - List of UserTariff objects
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public List<UserTariff> getUserTariffByUserId(int userId) throws NamingException, SQLException {
        try {
            return userTariffDao.getUserTariffByUserId(userId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Execute SELECT query.
     * Generate single UserTariff object which have user by user_id and tariff_id
     *
     * @param userId   - primary key of USER table
     * @param tariffId - primary key of USER table
     * @return - UserTariff object
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException {
        try {
            return userTariffDao.getUserTariffByTariffIdUserId(tariffId, userId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Execute DELETE query in USERS_PLAN table row by user_id and tariff_id.
     * As result we have USERS_PLAN table without row that have user_id as userId and tariff_id as tariffId.
     * Rollback connection if row with  such @param is not exist.
     *
     * @param userId   - primary key of USER table
     * @param tariffId - primary key of USER table
     * @return - true if delete is successfully and false if not.
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public boolean deleteByUseIdTariffId(int userId, int tariffId) throws NamingException, SQLException {
        try {
            return userTariffDao.deleteByUserIdTariffId(userId, tariffId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Checking month payment for tariff by user.
     * If current date after day of next bill in UserTariff object
     * then will be update status of user in User object to blocked (0).
     *
     * @param userId - primary key of USER table
     * @return true if update is successfully and false if not.
     * @throws SQLException    - if query is incorrect or connection is failed.
     * @throws NamingException - if troubles with connection.
     */
    @Override
    public boolean checkForMonthPayment(int userId) throws SQLException, NamingException {
        List<UserTariff> list = userTariffDao.getUserTariffByUserId(userId);
        list.stream().filter(o -> Objects.nonNull(o.getNextBill()))
                .filter(o -> CommandUtil.getDate().after(o.getNextBill()))
                .forEach(o -> {
                    try {
                        o.setStatus(0);
                        userTariffDao.update(o);
                        var user = userDao.getById(o.getUserId());
                        user.setStatus(0);
                        userDao.update(user);
                    } catch (SQLException | NamingException | NotFoundServiceIdException throwables) {
                        throwables.printStackTrace();
                    }
                });
        return true;
    }
}
