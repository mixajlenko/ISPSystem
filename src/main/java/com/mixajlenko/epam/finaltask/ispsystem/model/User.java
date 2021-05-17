package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Objects;

public class User extends Model {

    private int id;
    private int role_id;
    private String name;
    private String phone;
    private String email;

    public User() {
    }

    public User(String name, String phone, String email, int role_id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role_id = role_id;
    }

    public User(int id, String name, String phone, String email, int role_id) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role_id = role_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && role_id == user.role_id && Objects.equals(name, user.name) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role_id, name, phone, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
