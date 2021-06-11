package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdminUserCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start execution AdminUserCommand");
        var factory = ServiceFactory.getInstance();

        try {
            IUserService userService = factory.getUserService();
            IUserTariffService userTariffService = factory.getUserTariffService();

            String command = request.getParameter("command");
            String operation = request.getParameter("operation");
            String tariffId = request.getParameter("Tid");
            String userId = request.getParameter("Uid");
            if (Objects.nonNull(userId) && Objects.nonNull(operation) && Objects.nonNull(tariffId)) {
                var userTariff = userTariffService.getUserTariffByTariffIdUserId(Integer.parseInt(tariffId), Integer.parseInt(userId));
                switch (operation) {
                    case "Activate":
                        userTariff.setStatus(1);
                        userTariffService.update(userTariff);
                        logger.info("Activate command execute");
                        break;
                    case "Deactivate":
                        userTariff.setStatus(0);
                        userTariffService.update(userTariff);
                        logger.info("Deactivate command execute");
                        break;
                    case "Unsubscribe":
                        userTariffService.deleteByUseIdTariffId(Integer.parseInt(userId), Integer.parseInt(tariffId));
                        logger.info("Unsubscribe command execute");
                        break;
                    default:
                }
            }
            if (Objects.nonNull(command)) {
                var id = Integer.parseInt(userId);
                var user = userService.getById(id);
                switch (command) {
                    case "delete":
                        logger.info("delete command execute");
                        userService.delete(id);
                        break;
                    case "unlock":
                        logger.info("unlock command execute");
                        user.setStatus(1);
                        userService.update(user);
                        break;
                    case "block":
                        logger.info("block command execute");
                        user.setStatus(0);
                        userService.update(user);
                        break;
                    case "more":
                        logger.info("more command execute");
                        Map<Tariff, UserTariff> userTariffMap = new HashMap<>();
                        List<Tariff> lll = userTariffService.getUserTariffList(user.getId());
                        for (Tariff tariff : lll) {
                            var userTariffs = userTariffService.getUserTariffByTariffIdUserId(tariff.getId(), user.getId());
                            userTariffMap.put(tariff, userTariffs);
                        }
                        request.setAttribute("userTariffs", userTariffMap);
                        request.setAttribute("moreInfo", true);
                        break;
                    default:
                }

            }

            List<User> users = userService.getAll();
            request.setAttribute("users", users);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        logger.info("go to userPageAdmin");
        CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/userPageAdmin.jsp");
    }
}
