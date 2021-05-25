package com.mixajlenko.epam.finaltask.ispsystem.dao;

import com.mixajlenko.epam.finaltask.ispsystem.model.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IServiceDao extends IEntityDAO<Integer, Service> {

    Service getByName(String name) throws NamingException, SQLException;

}
