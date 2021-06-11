package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminMenuCommand implements ICommand {

   private static final Logger logger = Logger.getLogger(AdminMenuCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("AdminMenuCommand start");
        var factory = ServiceFactory.getInstance();
        try {

            var userService = factory.getUserService();
            var tariffService = factory.getTariffService();
            var userTariffService = factory.getUserTariffService();

            List<Tariff> tariffs = tariffService.getAll();
            List<User> users = userService.getAll();
            Set<Integer> allTariffs = new HashSet<>();

            for (UserTariff userTariff : userTariffService.getAll()) {
                if (userTariff.getStatus() != 0)
                    allTariffs.add(userTariff.getTariffId());
            }

            req.setAttribute("blocked", userService.blockedAccounts(users));
            req.setAttribute("active", users.size() - userService.blockedAccounts(users));
            req.setAttribute("users", users.size());
            req.setAttribute("AllTariffs", tariffs.size());
            req.setAttribute("ActiveTariffs", allTariffs.size());
            req.setAttribute("NonActiveTariffs", tariffs.size() - allTariffs.size());

            logger.info("go to mainPageAdmin");

            CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/mainPageAdmin.jsp");
        } catch (NamingException | SQLException e) {
            logger.warn(e.getMessage());
        }
    }
}
