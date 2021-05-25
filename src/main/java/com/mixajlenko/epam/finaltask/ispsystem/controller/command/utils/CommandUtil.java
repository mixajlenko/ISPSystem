package com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

public abstract class CommandUtil {

    private static Logger logger = Logger.getLogger(CommandUtil.class);

    public static void goToPage(HttpServletRequest req, HttpServletResponse resp, String url) {
        logger.info("go to page start");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
        logger.info(url);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }

    public static String getUserPageByRole(int accessLevel) {
        String page = "";
        logger.info("user by role");
        switch (accessLevel) {
            case 0:
                page = "/WEB-INF/view/admin/mainPageAdmin.jsp";
                break;
            case 1:
                page = "/WEB-INF/view/client/mainPageUser.jsp";
                break;
            default:
        }
        logger.info(page);
        return page;
    }

}
