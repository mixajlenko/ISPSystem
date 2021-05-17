package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Objects;

public class Tariff extends Model {

    private int id;
    private String name;
    private String description;
    private int price;

    public Tariff() {
    }

    public Tariff(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Tariff(int id, String name, String description, int price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return id == tariff.id && Double.compare(tariff.price, price) == 0 && Objects.equals(name, tariff.name) && Objects.equals(description, tariff.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
