package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AdminMenuCommand implements ICommand {

    Logger logger = Logger.getLogger(AdminMenuCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory factory = ServiceFactory.getInstance();
        try {
            IUserService userService = factory.getUserService();
            List<User> users = userService.getAll();
            req.setAttribute("blocked", userService.blockedAccounts(users));
            req.setAttribute("active", users.size() - userService.blockedAccounts(users));
            req.setAttribute("users", users.size());
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        logger.info("in page admin");
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/mainPageAdmin.jsp");
    }
}
