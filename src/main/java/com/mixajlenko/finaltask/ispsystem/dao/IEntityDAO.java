package com.mixajlenko.finaltask.ispsystem.dao;

import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.model.Model;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IEntityDAO<T, E extends Model> {

    E getById(T id) throws SQLException, NamingException, NotFoundServiceIdException;

    List<E> getAll() throws SQLException, NamingException;

    boolean update(E entity) throws SQLException, NamingException;

    boolean delete(T id) throws SQLException, NamingException;

    boolean add(E entity) throws SQLException, NamingException;

}
