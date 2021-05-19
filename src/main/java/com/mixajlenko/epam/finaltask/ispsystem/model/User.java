package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Objects;

public class User extends Model {

    private int id;
    private int roleId;
    private String firstName;
    private String secondName;
    private String phone;
    private String email;

    public User() {
    }

    public User(String firstName, String secondName, String phone, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
    }

    public User(String firstName, String secondName, String phone, String email, int roleId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.roleId = roleId;
    }

    public User(int id, String firstName, String secondName, String phone, String email, int roleId) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.roleId = roleId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, firstName, secondName, phone, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role_id=" + roleId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
