package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface ITariffService extends ItemService<Integer, Tariff> {

    boolean setServiceTariff(int serviceId, int tariffId) throws SQLException, NamingException;

    List<Tariff> getServiceTariff(int serviceId) throws SQLException, NamingException;

}
