package com.mixajlenko.epam.finaltask.ispsystem.model.dao.factory;

public abstract class ConnectionSupport {

    protected DaoFactory dataSource = null;

    public ConnectionSupport(DaoFactory dataSource) {
        this.dataSource = dataSource;
    }

}
