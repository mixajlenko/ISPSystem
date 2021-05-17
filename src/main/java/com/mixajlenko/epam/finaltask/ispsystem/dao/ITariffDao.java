package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;

import java.sql.SQLException;
import java.util.List;

public interface ITariffDao extends IEntityDAO<Integer, Tariff> {

    boolean setServiceTariff(int serviceId, int tariffId);

    boolean getServiceTariff(int serviceId);

}