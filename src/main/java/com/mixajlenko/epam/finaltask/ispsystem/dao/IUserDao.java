package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao extends IEntityDAO<Integer, User> {

    boolean setUserService(User user, int servicePlanId) throws SQLException;

    List<Service> getUserService(int userId);

    User getUserByName(String name);

    User getUserByEmail(String email);

}
