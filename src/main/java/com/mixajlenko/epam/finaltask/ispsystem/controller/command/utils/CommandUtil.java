package com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class CommandUtil {

    private static Logger logger = Logger.getLogger(CommandUtil.class);

    public static void goToPage(HttpServletRequest req, HttpServletResponse resp, String url) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }

    public static String getUserPageByRole(int accessLevel) {
        String page = "";
        switch (accessLevel) {
            case 0:
                page = "/";
                break;
            case 1:
                page = "/";
                break;
            default:
        }
        return page;
    }

}
