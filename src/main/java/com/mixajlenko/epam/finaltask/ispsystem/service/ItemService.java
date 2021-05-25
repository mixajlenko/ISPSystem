package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Model;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface ItemService<K, T extends Model> {

    List<T> getAll() throws SQLException, NamingException;

    T getById(K id) throws SQLException, NamingException;

    boolean add(T entity) throws SQLException, NamingException;

    T update(T entity) throws SQLException, NamingException;

    boolean delete(K id) throws SQLException, NamingException;

}
