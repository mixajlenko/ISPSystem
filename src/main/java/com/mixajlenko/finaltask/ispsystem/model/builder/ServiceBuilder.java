package com.mixajlenko.finaltask.ispsystem.model.builder;

import com.mixajlenko.finaltask.ispsystem.model.Payment;
import com.mixajlenko.finaltask.ispsystem.model.Service;

public interface ServiceBuilder {

    ServiceBuilder setId(int id);

    ServiceBuilder setName(String name);

    ServiceBuilder setDescription(String description);

    Service build();

}
