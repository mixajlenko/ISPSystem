package com.mixajlenko.epam.finaltask.ispsystem.model.dao.entity;

import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String phone;
    private String email;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email);
    }

    @Override
    public String toString() {
        return
                "Name: " + name + "\n" +
                        "Phone: " + phone + "\n" +
                        "Email: " + email + "\n" + "============";
    }
}
