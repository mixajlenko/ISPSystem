package com.mixajlenko.epam.finaltask.ispsystem.controller.command;


import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class LogoutCommand implements ICommand {

    private static Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("logout");

        request.getSession().invalidate();

        CommandUtil.goToPage(request, response, "/login.jsp");
    }
}
