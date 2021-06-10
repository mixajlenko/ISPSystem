package com.mixajlenko.finaltask.ispsystem.controller.command;


import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LogoutCommand implements ICommand {

    private static Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("logout");

        request.getSession().invalidate();

        CommandUtil.goToPage(request, response, "/login.jsp");
    }
}
