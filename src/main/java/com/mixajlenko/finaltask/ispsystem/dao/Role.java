package com.mixajlenko.finaltask.ispsystem.dao;

import com.mixajlenko.finaltask.ispsystem.model.User;

public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRole();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
