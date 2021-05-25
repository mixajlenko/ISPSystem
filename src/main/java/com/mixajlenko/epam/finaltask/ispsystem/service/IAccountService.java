package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IAccountService extends ItemService<Integer, Account> {

    Account getUserId(int id) throws SQLException, NamingException;

    int blockedAccounts(List<Account> accounts) throws NamingException, SQLException;

}
