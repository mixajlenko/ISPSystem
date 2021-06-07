package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class TariffServiceImpl implements ITariffService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private ITariffDao tariffDao = daoFactory.getTariffDao();

    @Override
    public List<Tariff> getAll() throws SQLException, NamingException {
        try {
            return tariffDao.getAll();
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tariff getById(Integer id) throws SQLException, NamingException {
        try {
            return tariffDao.getById(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean add(Tariff entity) throws SQLException, NamingException {
        try {
            return tariffDao.add(entity);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Tariff entity) throws SQLException, NamingException {
        try {
            ;
            return tariffDao.update(entity);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        try {
            tariffDao.delete(id);
            return true;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean setServiceTariff(int serviceId, int tariffId) throws SQLException, NamingException {
        try {
            tariffDao.setServiceTariff(serviceId, tariffId);
            return true;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tariff> getServiceTariff(int serviceId) throws SQLException, NamingException {
        try {
            return tariffDao.getServiceTariff(serviceId);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tariff getByName(String name) throws SQLException, NamingException {
        try {
            return tariffDao.getByName(name);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }
}
