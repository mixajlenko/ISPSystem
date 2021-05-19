package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface IAccountService extends ItemService<Integer, Account>{

    Account getUserId(int id) throws SQLException, NamingException;

}
