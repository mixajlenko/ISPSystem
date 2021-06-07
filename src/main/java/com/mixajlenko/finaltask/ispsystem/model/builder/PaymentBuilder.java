package com.mixajlenko.finaltask.ispsystem.model.builder;

import com.mixajlenko.finaltask.ispsystem.model.Payment;

import java.sql.Date;

public interface PaymentBuilder {

    PaymentBuilder setId(int id);

    PaymentBuilder setUserId(int userId);

    PaymentBuilder setBill(int bill);

    PaymentBuilder setStatus(int status);

    PaymentBuilder setBalance(int balance);

    PaymentBuilder setType(String type);

    PaymentBuilder setDate(Date date);

    Payment build();

}
