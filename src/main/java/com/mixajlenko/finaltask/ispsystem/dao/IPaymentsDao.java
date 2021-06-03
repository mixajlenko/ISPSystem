package com.mixajlenko.finaltask.ispsystem.dao;

import com.mixajlenko.finaltask.ispsystem.model.Payment;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IPaymentsDao extends IEntityDAO<Integer, Payment> {

    List<Payment> getAllById(int id) throws NamingException, SQLException;
}
