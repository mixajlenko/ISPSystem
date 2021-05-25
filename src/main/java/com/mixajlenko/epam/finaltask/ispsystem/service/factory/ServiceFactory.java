package com.mixajlenko.epam.finaltask.ispsystem.service.factory;

import com.mixajlenko.epam.finaltask.ispsystem.service.IAccountService;
import com.mixajlenko.epam.finaltask.ispsystem.service.IServiceService;
import com.mixajlenko.epam.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.epam.finaltask.ispsystem.service.UserService;
import com.mixajlenko.epam.finaltask.ispsystem.service.impl.AccountServiceImpl;
import com.mixajlenko.epam.finaltask.ispsystem.service.impl.ServiceServiceImpl;
import com.mixajlenko.epam.finaltask.ispsystem.service.impl.TariffServiceImpl;
import com.mixajlenko.epam.finaltask.ispsystem.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class ServiceFactory {

    private static Logger logger = Logger.getLogger(ServiceFactory.class);

    private UserService userService = new UserServiceImpl();
    private IAccountService accountService = new AccountServiceImpl();
    private IServiceService serviceService = new ServiceServiceImpl();
    private ITariffService tariffService = new TariffServiceImpl();

    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            logger.info("Create ServiceFactory");
            instance = new ServiceFactory();
        }
        return instance;
    }

    private ServiceFactory() {

    }

    public UserService getUserService() {
        logger.info("Get UserService");
        return userService;
    }

    public IAccountService getAccountService() {
        logger.info("Get AccountService");
        return accountService;
    }

    public IServiceService getServiceService() {
        logger.info("Get ServiceService");
        return serviceService;
    }

    public ITariffService getTariffService() {
        logger.info("Get Tariff Service");
        return tariffService;
    }

}
