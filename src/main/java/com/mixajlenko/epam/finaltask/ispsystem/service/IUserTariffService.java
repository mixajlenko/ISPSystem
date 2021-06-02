package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.model.UserTariff;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IUserTariffService extends ItemService<Integer, UserTariff>{

    List<UserTariff> getAllUserTariffByUserId(int userId) throws NamingException, SQLException;

    List<Tariff> getUserTariffList(int userId) throws NamingException, SQLException;

    UserTariff getUserTariffByUserId(int userId) throws NamingException, SQLException;

    UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException;
}
