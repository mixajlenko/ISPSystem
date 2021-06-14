package com.mixajlenko.finaltask.ispsystem.dao;

import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceNameException;
import com.mixajlenko.finaltask.ispsystem.model.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface IServiceDao extends IEntityDAO<Integer, Service> {

    Service getByName(String name) throws NamingException, SQLException, NotFoundServiceNameException;

}
