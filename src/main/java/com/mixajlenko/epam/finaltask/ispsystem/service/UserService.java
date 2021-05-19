package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface UserService extends ItemService<Integer, User> {

    boolean setUserService(User user, int servicePlanId) throws SQLException;

    List<Service> getUserService(int userId);

    User getUserByName(String name);

    User getUserByEmail(String email) throws SQLException, NamingException;

    User getByLoginAndPass(String login, String password) throws SQLException, NamingException;

}
