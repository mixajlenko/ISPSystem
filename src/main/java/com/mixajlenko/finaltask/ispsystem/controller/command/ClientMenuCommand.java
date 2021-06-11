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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClientMenuCommand implements ICommand {

   private static final Logger logger = Logger.getLogger(ClientMenuCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var factory = ServiceFactory.getInstance();
        try {
            IUserTariffService userTariffService = factory.getUserTariffService();
            IUserService userService = factory.getUserService();
            ITariffService tariffService = factory.getTariffService();
            var userq = (User) req.getSession().getAttribute("user");
            userTariffService.checkForMonthPayment(userq.getId());
            var user = userService.getById(userq.getId());
            req.getSession().setAttribute("user", user);
            String command = req.getParameter("command");
            String tariffId = req.getParameter("id");
            String tId = req.getParameter("Tid");

            if (Objects.nonNull(tId)){
                var tariff =  tariffService.getById(Integer.valueOf(tId));
                req.setAttribute("tariff", tariff);
                req.setAttribute("showTariff", true);
            }
            if (Objects.nonNull(command) && command.equals("unsubscribe") && Objects.nonNull(tariffId)) {
                userTariffService.deleteByUseIdTariffId(user.getId(), Integer.parseInt(tariffId));
            }
            Map<Tariff, UserTariff> userTariffMap = new HashMap<>();
            List<Tariff> lll = userTariffService.getUserTariffList(user.getId());
            for (Tariff tariff : lll) {
                var userTariff = userTariffService.getUserTariffByTariffIdUserId(tariff.getId(), user.getId());
                userTariffMap.put(tariff, userTariff);
            }
            if(userTariffMap.isEmpty()){
                req.setAttribute("emptyTariffs", true);
            }
            req.setAttribute("userTariffs", userTariffMap);
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/client/mainPageUser.jsp");
    }
}
