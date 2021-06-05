package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdminUserCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory factory = ServiceFactory.getInstance();

        try {
            IUserService userService = factory.getUserService();
            IUserTariffService userTariffService = factory.getUserTariffService();

            String command = request.getParameter("command");
            String operation = request.getParameter("operation");
            String tariffId = request.getParameter("Tid");
            String userId = request.getParameter("Uid");
            if (Objects.nonNull(userId) && Objects.nonNull(operation) && Objects.nonNull(tariffId)) {
                UserTariff userTariff = userTariffService.getUserTariffByTariffIdUserId(Integer.parseInt(tariffId), Integer.parseInt(userId));
                switch (operation) {
                    case "Activate":
                        userTariff.setStatus(1);
                        userTariffService.update(userTariff);
                        break;
                    case "Deactivate":
                        userTariff.setStatus(0);
                        userTariffService.update(userTariff);
                        break;
                    case "Unsubscribe":
                        userTariffService.deleteByUseIdTariffId(Integer.parseInt(userId), Integer.parseInt(tariffId));
                        break;
                    default:
                }
            }
            if (Objects.nonNull(command)) {
                int id = Integer.parseInt(userId);
                User user = userService.getById(id);
                switch (command) {
                    case "delete":
                        userService.delete(id);
                        break;
                    case "unlock":
                        user.setStatus(1);
                        System.out.println(user);
                        userService.update(user);
                        break;
                    case "block":
                        user.setStatus(0);
                        userService.update(user);
                        break;
                    case "more":
                        Map<Tariff, UserTariff> userTariffMap = new HashMap<>();
                        List<Tariff> lll = userTariffService.getUserTariffList(user.getId());
                        for (Tariff tariff : lll) {
                            UserTariff sss = userTariffService.getUserTariffByTariffIdUserId(tariff.getId(), user.getId());
                            userTariffMap.put(tariff, sss);
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

        CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/userPageAdmin.jsp");
    }
}
