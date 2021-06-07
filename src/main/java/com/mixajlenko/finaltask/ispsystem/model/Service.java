package com.mixajlenko.finaltask.ispsystem.model;

import com.mixajlenko.finaltask.ispsystem.model.builder.ServiceBuilder;

import java.util.Objects;

public class Service extends Model {

    private String name;
    private String description;

    private Service(Service.ServiceBuilderImpl builder) {
        super(builder.id);
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class ServiceBuilderImpl implements ServiceBuilder {

        private int id;
        private String name;
        private String description;

        @Override
        public ServiceBuilder setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public ServiceBuilder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public ServiceBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        @Override
        public Service build() {
            return new Service(this);
        }
    }

    public Service() {
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
        return Objects.equals(name, service.name) && Objects.equals(description, service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
