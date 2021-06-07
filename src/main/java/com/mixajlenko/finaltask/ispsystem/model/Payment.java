package com.mixajlenko.finaltask.ispsystem.model;

import com.mixajlenko.finaltask.ispsystem.model.builder.PaymentBuilder;

import java.awt.print.Book;
import java.sql.Date;
import java.util.Objects;

public class Payment extends Model {

    private int userId;
    private int bill;
    private int status;
    private int balance;
    private Date date;
    private String type;

    private Payment(Payment.PaymentsBuilderImpl builder) {
        super(builder.id);
        this.userId = builder.userId;
        this.bill = builder.bill;
        this.status = builder.status;
        this.balance = builder.balance;
        this.date = builder.date;
        this.type = builder.type;
    }

    public static class PaymentsBuilderImpl implements PaymentBuilder {

        private int id;
        private int userId;
        private int bill;
        private int status;
        private int balance;
        private Date date;
        private String type;

        @Override
        public PaymentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public PaymentBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public PaymentBuilder setBill(int bill) {
            this.bill = bill;
            return this;
        }

        @Override
        public PaymentBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        @Override
        public PaymentBuilder setBalance(int balance) {
            this.balance = balance;
            return this;
        }

        @Override
        public PaymentBuilder setType(String type) {
            this.type = type;
            return this;
        }

        @Override
        public PaymentBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        @Override
        public Payment build() {
            return new Payment(this);
        }
    }

    public Payment() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return userId == payment.userId && bill == payment.bill && status == payment.status && balance == payment.balance && Objects.equals(date, payment.date) && Objects.equals(type, payment.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bill, status, balance, date, type);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "userId=" + userId +
                ", bill=" + bill +
                ", status=" + status +
                ", balance=" + balance +
                ", date=" + date +
                ", type='" + type + '\'' +
                '}';
    }
}
