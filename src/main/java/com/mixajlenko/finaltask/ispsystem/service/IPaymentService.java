package com.mixajlenko.finaltask.ispsystem.service;

import com.mixajlenko.finaltask.ispsystem.model.Payment;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IPaymentService extends ItemService<Integer, Payment> {

    List<Payment> getAllByUserId(int id) throws NamingException, SQLException;

}
