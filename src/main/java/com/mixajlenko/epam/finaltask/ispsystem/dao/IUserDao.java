package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao extends IEntityDAO<Integer, User> {

    boolean setUserService(User user, int servicePlanId) throws SQLException, NamingException;

    List<Service> getUserService(int userId) throws NamingException, SQLException;

    User getUserByName(String name) throws NamingException, SQLException;

    User getUserByEmail(String email) throws NamingException, SQLException;

}
