package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Objects;

public class Account extends Model {

    private int id;
    private int userId;
    private int status;
    private int wallet;
    private String password;

    public Account() {
    }

    public Account(int userId, int status, int wallet, String password) {
        this.userId = userId;
        this.status = status;
        this.wallet = wallet;
        this.password = password;
    }

    public Account(int id, int userId, int status, int wallet, String password) {
        super(id);
        this.userId = userId;
        this.status = status;
        this.wallet = wallet;
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && userId == account.userId && status == account.status && wallet == account.wallet && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, status, wallet, password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", wallet=" + wallet +
                ", password='" + password + '\'' +
                '}';
    }
}
