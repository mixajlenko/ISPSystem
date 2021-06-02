package com.mixajlenko.epam.finaltask.ispsystem.dao.factory;

import com.mixajlenko.epam.finaltask.ispsystem.dao.*;

public abstract class DaoFactory {

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseDaoFactory();
        }
        return instance;
    }

    public abstract IServiceDao getServiceDao();

    public abstract ITariffDao getTariffDao();

    public abstract IUserDao getUserDao();

    public abstract IPaymentsDao getPaymentDao();

    public abstract IUserTariffDao getUserTariffDao();

}
