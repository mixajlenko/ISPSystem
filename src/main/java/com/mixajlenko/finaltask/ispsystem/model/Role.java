package com.mixajlenko.finaltask.ispsystem.model;

public enum Role {
    ADMIN(0),
    CLIENT(1);

    private int accessLevel;

    Role(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
