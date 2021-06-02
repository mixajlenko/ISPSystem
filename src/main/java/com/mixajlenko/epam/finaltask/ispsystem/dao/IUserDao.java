package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Payment;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao extends IEntityDAO<Integer, User> {

    User getUserByName(String name) throws NamingException, SQLException;

    User getUserByEmail(String email) throws NamingException, SQLException;

}
