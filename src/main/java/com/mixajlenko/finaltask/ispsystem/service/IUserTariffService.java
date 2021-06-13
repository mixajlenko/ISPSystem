package com.mixajlenko.finaltask.ispsystem.service;

import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IUserTariffService extends ItemService<Integer, UserTariff>{

    List<Tariff> getUserTariffList(int userId) throws NamingException, SQLException;

    List<UserTariff> getUserTariffByUserId(int userId) throws NamingException, SQLException;

    UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException;

    boolean deleteByUseIdTariffId(int userId, int tariffId) throws NamingException, SQLException;

    boolean checkForMonthPayment(int userId) throws SQLException, NamingException;
}
