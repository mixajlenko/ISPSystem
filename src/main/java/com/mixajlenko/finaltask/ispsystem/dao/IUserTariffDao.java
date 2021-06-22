package com.mixajlenko.finaltask.ispsystem.dao;

import com.mixajlenko.finaltask.ispsystem.model.UserTariff;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IUserTariffDao extends IEntityDAO<Integer, UserTariff> {

    List<UserTariff> getUserTariffByUserId(int userId) throws NamingException, SQLException;

    UserTariff getUserTariffByTariffIdUserId(int tariffId, int userId) throws NamingException, SQLException;

    boolean deleteByUserIdTariffId(int userId, int tariffId) throws NamingException, SQLException;

}
