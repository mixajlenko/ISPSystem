package com.mixajlenko.finaltask.ispsystem.dao;

import com.mixajlenko.finaltask.ispsystem.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface IUserDao extends IEntityDAO<Integer, User> {

    User getUserByName(String name) throws NamingException, SQLException;

    User getUserByEmail(String email) throws NamingException, SQLException;

}
