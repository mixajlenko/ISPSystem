package com.mixajlenko.epam.finaltask.ispsystem.model.dao.manager;

import java.sql.SQLException;
import java.util.List;

public interface IEntityDAO<E> {

    E getById(int id) throws SQLException;

    List<E> getAll() throws SQLException;

    boolean updateEntity(E entity) throws SQLException;

    boolean deleteEntity(int id) throws SQLException;

    boolean insertEntity(E entity) throws SQLException;

}
