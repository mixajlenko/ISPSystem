package com.mixajlenko.finaltask.ispsystem.service;

import com.mixajlenko.finaltask.ispsystem.model.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface IServiceService extends ItemService<Integer, Service> {

    Service getByName(String name) throws SQLException, NamingException;

}
