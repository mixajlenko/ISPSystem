package com.mixajlenko.epam.finaltask.ispsystem.model.dao.entity;

import java.util.Objects;

public class Account {

    private int id;
    private int id_user;
    private double wallet;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && id_user == account.id_user && Double.compare(account.wallet, wallet) == 0 && Objects.equals(state, account.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_user, wallet, state);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", wallet=" + wallet +
                ", state='" + state + '\'' +
                '}';
    }
}
