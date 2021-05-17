package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.dao.IAccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory;
import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

//        DaoFactory daoFactory = DaoFactory.getInstance();
//
//        String name = request.getParameter("name");
//        String phone = request.getParameter("phone");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        //Add validators and exceptions
//
//        IUserDao userDao = daoFactory.getUserDao();
//        IAccountDao accountDao = daoFactory.getAccountDao();
//        User user = new User(name, phone, email);
//        try {
//            userDao.add(user);
//        } catch (SQLException | NamingException throwables) {
//            throwables.printStackTrace();
//        }
//
//        Account account = new Account(user.getId(), 2, 0, password);
//
//        try {
//            accountDao.add(account);
//        } catch (SQLException | NamingException throwables) {
//            throwables.printStackTrace();
//        }
//
//        request.getSession().setAttribute("user", user);
//
////        String page = CommandUtil.getUserPageByRole(user.getAccessLevel());
//
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("src/main/webapp/WEB-INF/views/homePage.jsp");
//        try {
//            requestDispatcher.forward(request, response);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
    }
}
