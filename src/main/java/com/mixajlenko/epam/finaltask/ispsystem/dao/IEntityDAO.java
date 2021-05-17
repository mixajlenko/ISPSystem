package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Model;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IEntityDAO<T, E extends Model> {

    E getById(T id) throws SQLException, NamingException;

    List<E> getAll() throws SQLException, NamingException;

    boolean update(E entity) throws SQLException, NamingException;

    boolean delete(T id) throws SQLException, NamingException;

    boolean add(E entity) throws SQLException, NamingException;

}
