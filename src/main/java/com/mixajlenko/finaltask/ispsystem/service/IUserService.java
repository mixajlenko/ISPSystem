package com.mixajlenko.finaltask.ispsystem.service;

import com.mixajlenko.finaltask.ispsystem.model.User;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IUserService extends ItemService<Integer, User> {

    User getUserByEmail(String email) throws SQLException, NamingException;

    User getByLoginAndPass(String login, String password) throws SQLException, NamingException;

    int blockedAccounts(List<User> accounts) throws NamingException, SQLException;

}
