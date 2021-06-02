package com.mixajlenko.epam.finaltask.ispsystem.service.impl;

import com.mixajlenko.epam.finaltask.ispsystem.dao.IPaymentsDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.epam.finaltask.ispsystem.exception.DataBaseException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Payment;
import com.mixajlenko.epam.finaltask.ispsystem.service.IPaymentService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class PaymentServiceImpl implements IPaymentService {

    private static Logger logger = Logger.getLogger(PaymentServiceImpl.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private IPaymentsDao paymentsDao = daoFactory.getPaymentDao();

    @Override
    public List<Payment> getAll() throws SQLException, NamingException {
        try {
            return paymentsDao.getAll();
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Payment getById(Integer id) throws SQLException, NamingException {
        try {
            return paymentsDao.getById(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean add(Payment entity) throws SQLException, NamingException {
        try {
            return paymentsDao.add(entity);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Payment update(Payment entity) throws SQLException, NamingException {
        try {
            paymentsDao.update(entity);
            return entity;
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        try {
            return paymentsDao.delete(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Payment> getAllById(int id) throws NamingException, SQLException {
        try {
            return paymentsDao.getAllById(id);
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }
}
