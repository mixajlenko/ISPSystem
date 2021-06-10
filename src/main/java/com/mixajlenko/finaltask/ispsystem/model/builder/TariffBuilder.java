package com.mixajlenko.finaltask.ispsystem.model.builder;

import com.mixajlenko.finaltask.ispsystem.model.Tariff;

public interface TariffBuilder {

    TariffBuilder setId(int id);

    TariffBuilder setName(String name);

    TariffBuilder setDescription(String description);

    TariffBuilder setPrice(int price);

    Tariff build();
}
