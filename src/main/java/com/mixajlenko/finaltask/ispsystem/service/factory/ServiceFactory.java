package com.mixajlenko.finaltask.ispsystem.service.factory;

import com.mixajlenko.finaltask.ispsystem.service.*;
import com.mixajlenko.finaltask.ispsystem.service.impl.*;
import com.mixajlenko.finaltask.ispsystem.service.*;
import com.mixajlenko.finaltask.ispsystem.service.impl.*;
import org.apache.log4j.Logger;

public class ServiceFactory {

    private static Logger logger = Logger.getLogger(ServiceFactory.class);

    private com.mixajlenko.finaltask.ispsystem.service.IUserService IUserService = new UserServiceImpl();
    private IServiceService serviceService = new ServiceServiceImpl();
    private ITariffService tariffService = new TariffServiceImpl();
    private IPaymentService paymentService = new PaymentServiceImpl();
    private IUserTariffService userTariffService = new UserTariffService();

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

    public IUserTariffService getUserTariffService() {
        logger.info("Get UserTariffService");
        return userTariffService;
    }

    public IPaymentService getPaymentService() {
        logger.info("Get PaymentService");
        return paymentService;
    }

    public IUserService getUserService() {
        logger.info("Get UserService");
        return IUserService;
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
