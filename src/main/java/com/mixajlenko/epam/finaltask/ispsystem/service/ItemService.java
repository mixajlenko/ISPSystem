package com.mixajlenko.epam.finaltask.ispsystem.service;

import com.mixajlenko.epam.finaltask.ispsystem.model.Model;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface ItemService<K, T extends Model> {

    T getById(K id) throws SQLException, NamingException;

    boolean add(T entity) throws SQLException, NamingException;

    T update(T entity);

    boolean delete(K id);

}
