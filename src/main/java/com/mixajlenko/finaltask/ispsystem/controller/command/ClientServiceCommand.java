package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Service;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.IServiceService;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.*;

public class ClientServiceCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory factory = ServiceFactory.getInstance();

        try {
            IServiceService serviceService = factory.getServiceService();
            ITariffService tariffService = factory.getTariffService();
            IUserService userService = factory.getUserService();
            IUserTariffService userTariffService = factory.getUserTariffService();
            String item = request.getParameter("item");
            String serviceId = request.getParameter("serviceId");
            String sortAction = request.getParameter("sort");
            String tariffId = request.getParameter("tariffId");
            int userId = (int) request.getSession().getAttribute("globalUserId");
            User user = userService.getById(userId);
            if (Objects.nonNull(tariffId) && userId != 0 && user.getStatus()==1) {
                    userTariffService.add(new UserTariff(userId, Integer.parseInt(tariffId)));
            }

            List<Tariff> tariffs = new ArrayList<>();
            if (serviceId != null) {
                tariffs = tariffService.getServiceTariff(Integer.parseInt(serviceId));
                if (Objects.nonNull(sortAction) && sortAction.equals("price"))
                    tariffs.sort(new Tariff.PriceComparator());
                if (Objects.nonNull(sortAction) && sortAction.equals("name"))
                    tariffs.sort(new Tariff.NameComparator());
                if (Objects.nonNull(sortAction) && sortAction.equals("nameR"))
                    tariffs.sort(new Tariff.NameReverseComparator());
            }

            if (item != null) {
                request.setAttribute("showTariffs", true);
            }


            List<Service> services = serviceService.getAll();
            List<Tariff> complex = new ArrayList<>();
            complex.add(tariffService.getById(1));
            complex.add(tariffService.getById(2));
            complex.add(tariffService.getById(3));

            request.setAttribute("services", services);
            request.setAttribute("item", item);
            request.setAttribute("paramId", serviceId);

            request.setAttribute("tariffs", tariffs);
            request.setAttribute("complex", complex);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        CommandUtil.goToPage(request, response, "/WEB-INF/view/client/servicePage.jsp");
    }
}
