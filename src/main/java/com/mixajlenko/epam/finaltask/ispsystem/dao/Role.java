package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.User;

public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRole_id();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
