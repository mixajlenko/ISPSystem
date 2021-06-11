package com.mixajlenko.finaltask.ispsystem.controller.command.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public abstract class CommandUtil {

    private static final Logger logger = Logger.getLogger(CommandUtil.class);

    private CommandUtil() {
    }

    public static void goToPage(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException {
        logger.info("go to page start");
        var requestDispatcher = req.getRequestDispatcher(url);

        logger.info(url);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }

    public static String getUserPageByRole(int accessLevel) {
        var page = "";
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
        var cal = Calendar.getInstance();
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Date.valueOf(sdf.format(cal.getTime()));
    }

    public static Date getNextBill() {
        var cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Date.valueOf(sdf.format(cal.getTime()));
    }

    public static Optional<String> encrypt(String pass, boolean isVerify) {
        var bcryptHashString = BCrypt.withDefaults().hashToString(12, pass.toCharArray());
        if (isVerify) {
            BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), bcryptHashString);
            return Optional.of(String.valueOf(result.verified));
        }
        return Optional.of(bcryptHashString);
    }

    public static boolean verifyPass(String pass, String userPass){
        BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), userPass);
        return result.verified;
    }

    public static StringBuilder generateDownloadFileBuffer(String serviceName, List<Tariff> tariffs) {
        var writer = new StringBuilder();

        writer.append(serviceName);
        writer.append('\n');
        writer.append("Name");
        writer.append(",");
        writer.append("Description");
        writer.append(",");
        writer.append("Price");
        writer.append('\n');

        for (Tariff tariff : tariffs) {
            writer.append(tariff.getName());
            writer.append(",");
            writer.append(tariff.getDescription());
            writer.append(",");
            writer.append(tariff.getPrice());
            writer.append('\n');
        }

        return writer;
    }

}
