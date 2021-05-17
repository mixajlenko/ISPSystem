package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import java.util.List;

public interface IUserDao extends IEntityDAO<Integer, User> {

    boolean setUserService(int userId, int servicePlanId);

    List<Service> getUserService(int userId);

}
