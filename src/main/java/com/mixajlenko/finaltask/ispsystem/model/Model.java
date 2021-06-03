package com.mixajlenko.finaltask.ispsystem.model;

public abstract class Model {

    private int id;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
