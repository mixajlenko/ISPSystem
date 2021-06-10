package com.mixajlenko.finaltask.ispsystem.model;

import com.mixajlenko.finaltask.ispsystem.model.builder.UserTariffBuilder;

import java.sql.Date;
import java.util.Objects;

public class UserTariff extends Model {

    private int userId;
    private int tariffId;
    private Date subDate;
    private int status;
    private Date nextBill;

    public UserTariff() {
    }

    private UserTariff(UserTariff.UserTariffBuilderImpl builder) {

        super(builder.id);
        this.userId = builder.userId;
        this.tariffId = builder.tariffId;
        this.subDate = builder.subDate;
        this.status = builder.status;
        this.nextBill = builder.nextBill;

    }

    public static class UserTariffBuilderImpl implements UserTariffBuilder {

        private int id;
        private int userId;
        private int tariffId;
        private Date subDate;
        private int status;
        private Date nextBill;

        @Override
        public UserTariffBuilder setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public UserTariffBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public UserTariffBuilder setTariffId(int tariffId) {
            this.tariffId = tariffId;
            return this;
        }

        @Override
        public UserTariffBuilder setSubDate(Date subDate) {
            this.subDate = subDate;
            return this;
        }

        @Override
        public UserTariffBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        @Override
        public UserTariffBuilder setNextBill(Date nextBill) {
            this.nextBill = nextBill;
            return this;
        }

        @Override
        public UserTariff build() {
            return new UserTariff(this);
        }
    }

    public Date getSubDate() {
        return subDate;
    }

    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getNextBill() {
        return nextBill;
    }

    public void setNextBill(Date nextBill) {
        this.nextBill = nextBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTariff that = (UserTariff) o;
        return userId == that.userId && tariffId == that.tariffId && status == that.status && Objects.equals(subDate, that.subDate) && Objects.equals(nextBill, that.nextBill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, tariffId, subDate, status, nextBill);
    }

    @Override
    public String toString() {
        return "UserTariff{" +
                "userId=" + userId +
                ", tariffId=" + tariffId +
                ", subDate=" + subDate +
                ", status=" + status +
                ", nextBill=" + nextBill +
                '}';
    }
}
