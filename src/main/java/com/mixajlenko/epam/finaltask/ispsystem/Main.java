package com.mixajlenko.epam.finaltask.ispsystem;

import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.AccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.ServicesDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.TariffDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.manager.UserDao;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, NamingException {
        ServicesDao servicesDao = new ServicesDao();
        TariffDao tariffDao = new TariffDao();
        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();


//        servicesDao.add(new Service("testService", "testService"));
//        Service forUpdate = servicesDao.getById(5);
//        forUpdate.setName("AfterUpdateName");
//        servicesDao.update(forUpdate);
//        servicesDao.delete(4);
//        for (Service s : servicesDao.getAll()) {
//            System.out.println(s);
//        }

//        tariffDao.add(new Tariff("testTariff","testTariff", 100));
//        Tariff tariffForUpdate = tariffDao.getById(5);
//        tariffForUpdate.setName("AfterUpdateName");
//        tariffDao.update(tariffForUpdate);
//        tariffDao.delete(4);
//        for (Tariff s : tariffDao.getAll()) {
//            System.out.println(s);
//        }
//        tariffDao.setServiceTariff(servicesDao.getById(5).getId(), tariffDao.getById(5).getId());
        //System.out.println(tariffDao.getServiceTariff(5));
//
//
//
//        userDao.add(new User("TestUser", "testUser", "testuser",1));
        //   User userForUpdate = userDao.getById(5);
//        userForUpdate.setName("AfterUpdateName");
//        userDao.update(userForUpdate);
//        userDao.delete(4);
//        for (User s : userDao.getAll()) {
//            System.out.println(s);
//        }
        //System.out.println(userDao.getUserByEmail(userDao.getById(5).getEmail()));
//        System.out.println(userDao.getUserByName(userDao.getById(5).getName()));
        // userDao.setUserService(userForUpdate,1);
        // System.out.println(userDao.getUserService(userForUpdate.getId()));

    }
}
