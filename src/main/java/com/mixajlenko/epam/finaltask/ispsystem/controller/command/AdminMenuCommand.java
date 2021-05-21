package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class AdminMenuCommand implements ICommand {

    Logger logger = Logger.getLogger(AdminMenuCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        logger.info("in page admin");
        if (user.getRoleId() == 0) {
            String page = CommandUtil.getUserPageByRole(0);
            CommandUtil.goToPage(req, resp, page);
        }
    }
}
