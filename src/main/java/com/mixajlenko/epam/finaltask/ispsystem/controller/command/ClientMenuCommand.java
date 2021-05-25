package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ClientMenuCommand implements ICommand {
    Logger logger = Logger.getLogger(ClientMenuCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/client/mainPageUser.jsp");
    }
}
