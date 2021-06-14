package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LogoutCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start execution logout");

        request.getSession().invalidate();

        logger.info("go to page login");

        CommandUtil.goToPage(request, response, "/login.jsp");
    }
}
