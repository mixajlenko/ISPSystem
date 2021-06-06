package com.mixajlenko.finaltask.ispsystem.dao;

import com.mixajlenko.finaltask.ispsystem.model.Tariff;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface ITariffDao extends IEntityDAO<Integer, Tariff> {

    boolean setServiceTariff(int serviceId, int tariffId) throws SQLException, NamingException;

    List<Tariff> getServiceTariff(int serviceId) throws SQLException, NamingException;

    Tariff getByName(String name) throws NamingException, SQLException;

}