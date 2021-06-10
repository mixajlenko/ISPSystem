package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;
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

    Logger logger = Logger.getLogger(AdminMenuCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        try {
            IUserService userService = factory.getUserService();
            ITariffService tariffService = factory.getTariffService();
            IUserTariffService userTariffService = factory.getUserTariffService();
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
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        logger.info("in page admin");
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/mainPageAdmin.jsp");
    }
}
