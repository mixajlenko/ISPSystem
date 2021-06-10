package com.mixajlenko.finaltask.ispsystem.model.builder;

import com.mixajlenko.finaltask.ispsystem.model.UserTariff;

import java.sql.Date;

public interface UserTariffBuilder {

    UserTariffBuilder setId(int id);

    UserTariffBuilder setUserId(int userId);

    UserTariffBuilder setTariffId(int tariffId);

    UserTariffBuilder setSubDate(Date subDate);

    UserTariffBuilder setStatus(int status);

    UserTariffBuilder setNextBill(Date nextBill);

    UserTariff build();
}
