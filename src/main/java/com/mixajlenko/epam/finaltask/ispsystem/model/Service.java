package com.mixajlenko.epam.finaltask.ispsystem.model;

import java.util.Objects;

public class Service extends Model {

    private int id;
    private String name;
    private String description;

    public Service() {
    }

    public Service(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public Service(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id && Objects.equals(name, service.name) && Objects.equals(description, service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
