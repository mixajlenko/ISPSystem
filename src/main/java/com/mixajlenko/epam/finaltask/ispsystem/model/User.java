package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Comparator;
import java.util.Objects;

public class User extends Model {

    private int id;
    private String firstName;
    private String secondName;
    private String phone;
    private String email;
    private int status;
    private int wallet;
    private String password;
    private int role;

    public User() {
    }

    public User(String firstName, String secondName, String phone, String email, int status, int wallet, String password, int roleId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.wallet = wallet;
        this.password = password;
        this.role = roleId;
    }

    public User(String firstName, String secondName, String phone, String email, int status, int wallet, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.wallet = wallet;
        this.password = password;
    }

    public User(int id, String firstName, String secondName, String phone, String email, int status, int wallet, String password, int roleId) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.wallet = wallet;
        this.password = password;
        this.role = roleId;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && status == user.status && wallet == user.wallet && role == user.role && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, phone, email, status, wallet, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", wallet=" + wallet +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public static class FirstNameComparator implements Comparator<User> {
        @Override
        public int compare(User p1, User p2) {
            return p1.getFirstName().compareTo(p2.getFirstName());
        }
    }

    public static class SecondNameComparator implements Comparator<User> {
        @Override
        public int compare(User p1, User p2) {
            return p1.getSecondName().compareTo(p2.getSecondName());
        }
    }

    public static class PhoneComparator implements Comparator<User> {
        @Override
        public int compare(User p1, User p2) {
            return p1.getPhone().compareTo(p2.getPhone());
        }
    }
}
