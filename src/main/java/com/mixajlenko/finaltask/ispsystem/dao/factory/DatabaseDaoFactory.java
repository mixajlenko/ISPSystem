package com.mixajlenko.finaltask.ispsystem.dao.factory;

import com.mixajlenko.finaltask.ispsystem.dao.*;
import com.mixajlenko.finaltask.ispsystem.dao.manager.*;

import org.apache.log4j.Logger;

public class DatabaseDaoFactory extends DaoFactory {

    private static final Logger logger = Logger.getLogger(DatabaseDaoFactory.class);

    private final IServiceDao servicesDao = new ServicesDao();
    private final ITariffDao tariffDao = new TariffDao();
    private final IUserDao userDao = new UserDao();
    private final IPaymentsDao paymentDao = new PaymentsDao();
    private final IUserTariffDao userTariffDao = new UserTariffDao();

    @Override
    public IServiceDao getServiceDao() {
        logger.info("Get Service DAO");
        return servicesDao;
    }

    @Override
    public ITariffDao getTariffDao() {
        logger.info("Get Tariff DAO");
        return tariffDao;
    }

    @Override
    public IUserDao getUserDao() {
        logger.info("Get User DAO");
        return userDao;
    }

    @Override
    public IPaymentsDao getPaymentDao() {
        logger.info("Get Payment DAO");
        return paymentDao;
    }

    @Override
    public IUserTariffDao getUserTariffDao() {
        logger.info("Get UserTariff DAO");
        return userTariffDao;
    }
}
