package com.mixajlenko.finaltask.ispsystem.controller.servlet;

import com.mixajlenko.finaltask.ispsystem.controller.command.ICommand;
import com.mixajlenko.finaltask.ispsystem.controller.command.commandfactory.CommandFactory;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundOperationException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


import java.io.IOException;

public class Controller extends HttpServlet {
    private static Logger logger = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI();
        path = path.substring(path.indexOf("view") - 1);
        logger.info(path);
        ICommand command;
        try {
            command = CommandFactory.getCommand(path);
            logger.info(path);
            command.execute(req, resp);
        } catch (NotFoundOperationException e) {
            logger.info("not found in controller");
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/errorPage.jsp");
        }
    }
}