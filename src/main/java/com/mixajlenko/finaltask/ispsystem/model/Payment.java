package com.mixajlenko.finaltask.ispsystem.model;

import java.sql.Date;
import java.util.Objects;

public class Payment extends Model {

    private int id;
    private int userId;
    private int bill;
    private int status;
    private int balance;
    private Date date;
    private String type;

    public Payment() {
    }

    public Payment(int id, int userId, int bill, int status, int balance, Date date, String type) {
        super(id);
        this.userId = userId;
        this.bill = bill;
        this.status = status;
        this.balance = balance;
        this.date = date;
        this.type = type;
    }

    public Payment(int userId, int bill, int status, int balance, Date date, String type) {
        this.userId = userId;
        this.bill = bill;
        this.status = status;
        this.balance = balance;
        this.date = date;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && userId == payment.userId && bill == payment.bill && status == payment.status && balance == payment.balance && Objects.equals(date, payment.date) && Objects.equals(type, payment.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bill, status, balance, date, type);
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
                ", type='" + type + '\'' +
                '}';
    }
}
