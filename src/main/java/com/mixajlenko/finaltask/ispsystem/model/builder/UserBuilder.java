package com.mixajlenko.finaltask.ispsystem.model.builder;

import com.mixajlenko.finaltask.ispsystem.model.User;

public interface UserBuilder {

    UserBuilder setId(int id);

    UserBuilder setFirstName(String firstName);

    UserBuilder setSecondName(String secondName);

    UserBuilder setPhone(String phone);

    UserBuilder setEmail (String email);

    UserBuilder setStatus(int status);

    UserBuilder setWallet(int wallet);

    UserBuilder setPassword(String password);

    UserBuilder setRole(int role);

    User build();
}
