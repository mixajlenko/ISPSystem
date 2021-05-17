package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Account;

import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IAccountDao extends IEntityDAO<Integer, Account> {

    boolean encryptPass(Account account) throws NoSuchAlgorithmException, SQLException, NamingException;

}
