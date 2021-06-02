package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Objects;

public class Payment extends Model {

    private int id;
    private int userId;
    private int bill;
    private int status;
    private int balance;
    private String date;

    public Payment() {
    }

    public Payment(int id, int userId, int bill, int status, int balance, String date) {
        super(id);
        this.userId = userId;
        this.bill = bill;
        this.status = status;
        this.balance = balance;
        this.date = date;
    }

    public Payment(int userId, int bill, int status, int balance, String date) {
        this.userId = userId;
        this.bill = bill;
        this.status = status;
        this.balance = balance;
        this.date = date;
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

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && userId == payment.userId && bill == payment.bill && status == payment.status && balance == payment.balance && Objects.equals(date, payment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bill, status, balance, date);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId=" + userId +
                ", bill=" + bill +
                ", status=" + status +
                ", balance=" + balance +
                ", date='" + date + '\'' +
                '}';
    }

}
