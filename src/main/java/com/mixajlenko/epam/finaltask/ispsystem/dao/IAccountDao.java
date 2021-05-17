package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Account;

public interface IAccountDao extends IEntityDAO<Integer, Account> {

    boolean encryptPass(Account account);

}
