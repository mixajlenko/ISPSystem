package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.epam.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserTariffService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientMenuCommand implements ICommand {
    Logger logger = Logger.getLogger(ClientMenuCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory factory = ServiceFactory.getInstance();

        try {
            IUserTariffService userTariffService = factory.getUserTariffService();
            User user = (User) req.getSession().getAttribute("user");

            Map<Tariff, UserTariff> userTariffMap = new HashMap<>();
            List<Tariff> lll = userTariffService.getUserTariffList(user.getId());
            for(Tariff tariff : lll){
                UserTariff sss = userTariffService.getUserTariffByTariffIdUserId(tariff.getId(),user.getId());
                userTariffMap.put(tariff,sss);
            }

            req.setAttribute("userTariffs", userTariffMap);
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/client/mainPageUser.jsp");
    }
}
