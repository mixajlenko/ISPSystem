package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IUserService extends ItemService<Integer, User> {

    User getUserByName(String name);

    User getUserByEmail(String email) throws SQLException, NamingException;

    User getByLoginAndPass(String login, String password) throws SQLException, NamingException;

    int blockedAccounts(List<User> accounts) throws NamingException, SQLException;

}
