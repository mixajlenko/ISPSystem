package com.mixajlenko.epam.finaltask.ispsystem.dao.factory;

import com.mixajlenko.epam.finaltask.ispsystem.dao.IAccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IServiceDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.AccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.ServicesDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.TariffDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.UserDao;

import org.apache.log4j.Logger;

public class DatabaseDaoFactory extends DaoFactory {

    private static Logger logger = Logger.getLogger(DatabaseDaoFactory.class);

    private IServiceDao servicesDao = new ServicesDao();
    private ITariffDao tariffDao = new TariffDao();
    private IUserDao userDao = new UserDao();
    private IAccountDao accountDao = new AccountDao();

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
    public IAccountDao getAccountDao() {
        logger.info("Get AccountDao");
        return accountDao;
    }
}
