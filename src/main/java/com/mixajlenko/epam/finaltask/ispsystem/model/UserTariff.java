package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Objects;

public class UserTariff extends Model {

    private int id;
    private int userId;
    private int tariffId;
    private String subDate;
    private int status;
    private String nextBill;

    public UserTariff() {
    }

    public UserTariff(int userId, int tariffId, String subDate, int status, String nextBill) {
        this.userId = userId;
        this.tariffId = tariffId;
        this.subDate = subDate;
        this.status = status;
        this.nextBill = nextBill;
    }

    public UserTariff(int id, int userId, int tariffId,String subDate, int status, String nextBill) {
        super(id);
        this.userId = userId;
        this.tariffId = tariffId;
        this.subDate = subDate;
        this.status = status;
        this.nextBill = nextBill;
    }

    public UserTariff(int userId, int tariffId) {
        this.userId = userId;
        this.tariffId = tariffId;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
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

    public String getNextBill() {
        return nextBill;
    }

    public void setNextBill(String nextBill) {
        this.nextBill = nextBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTariff that = (UserTariff) o;
        return id == that.id && userId == that.userId && tariffId == that.tariffId && status == that.status && Objects.equals(subDate, that.subDate) && Objects.equals(nextBill, that.nextBill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, tariffId, subDate, status, nextBill);
    }

    @Override
    public String toString() {
        return "UserTariff{" +
                "id=" + id +
                ", userId=" + userId +
                ", tariffId=" + tariffId +
                ", subDate='" + subDate + '\'' +
                ", status=" + status +
                ", nextBill='" + nextBill + '\'' +
                '}';
    }
}
