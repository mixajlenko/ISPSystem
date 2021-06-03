package com.mixajlenko.finaltask.ispsystem.dao.factory;

import com.mixajlenko.finaltask.ispsystem.dao.*;
import com.mixajlenko.finaltask.ispsystem.dao.manager.*;

import com.mixajlenko.finaltask.ispsystem.dao.*;
import com.mixajlenko.finaltask.ispsystem.dao.manager.*;
import org.apache.log4j.Logger;

public class DatabaseDaoFactory extends DaoFactory {

    private static Logger logger = Logger.getLogger(DatabaseDaoFactory.class);

    private IServiceDao servicesDao = new ServicesDao();
    private ITariffDao tariffDao = new TariffDao();
    private IUserDao userDao = new UserDao();
    private IPaymentsDao paymentDao = new PaymentsDao();
    private IUserTariffDao userTariffDao = new UserTariffDao();

    @Override
    public IServiceDao getServiceDao() {
        logger.info("Get ServiceDao");
        return servicesDao;
    }

    @Override
    public ITariffDao getTariffDao() {
        logger.info("Get TariffDao");
        return tariffDao;
    }

    @Override
    public IUserDao getUserDao() {
        logger.info("Get UserDao");
        return userDao;
    }

    @Override
    public IPaymentsDao getPaymentDao() {
        logger.info("Get PaymentDao");
        return paymentDao;
    }

    @Override
    public IUserTariffDao getUserTariffDao() {
        logger.info("Get UserTariffDao");
        return userTariffDao;
    }
}
