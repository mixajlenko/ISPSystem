package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Payment;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IPaymentService extends ItemService<Integer, Payment>{
    List<Payment> getAllById(int id) throws NamingException, SQLException;
}
