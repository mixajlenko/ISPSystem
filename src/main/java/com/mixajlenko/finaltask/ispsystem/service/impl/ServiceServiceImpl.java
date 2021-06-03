package com.mixajlenko.finaltask.ispsystem.service.impl;

import com.mixajlenko.finaltask.ispsystem.dao.IServiceDao;
import com.mixajlenko.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.model.Service;
import com.mixajlenko.finaltask.ispsystem.service.IServiceService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class ServiceServiceImpl implements IServiceService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IServiceDao serviceDao = daoFactory.getServiceDao();

    @Override
    public List<Service> getAll() throws SQLException, NamingException {
        try {
            return serviceDao.getAll();
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Service getById(Integer id) throws SQLException, NamingException {
        try {
            return serviceDao.getById(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean add(Service entity) throws SQLException, NamingException {
        try {
            serviceDao.add(entity);
            return true;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Service update(Service entity) throws SQLException, NamingException {
        try {
            serviceDao.update(entity);
            return entity;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        try {
            serviceDao.delete(id);
            return true;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Service getByName(String name) throws SQLException, NamingException {
        try {
            return serviceDao.getByName(name);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }
}
