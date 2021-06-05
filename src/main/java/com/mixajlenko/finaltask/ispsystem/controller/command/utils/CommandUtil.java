package com.mixajlenko.finaltask.ispsystem.controller.command.utils;

//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
                page = "/view/admin/mainPageAdmin";
                break;
            case 1:
                page = "/view/client/mainPageUser";
                break;
            default:
        }
        logger.info(page);
        return page;
    }


    public static Date getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Date.valueOf(sdf.format(cal.getTime()));
    }

    public static Date getNextBill() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Date.valueOf(sdf.format(cal.getTime()));
    }

    public String hashPass(){
        return "";
    }

}
