package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Service;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.finaltask.ispsystem.service.IServiceService;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ClientServiceCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(ClientServiceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var factory = ServiceFactory.getInstance();

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
            var user = userService.getById(userId);
            if (Objects.nonNull(tariffId) && userId != 0 && user.getStatus()==1) {
                request.setAttribute("subSuccess", true);
                    userTariffService.add(new UserTariff.UserTariffBuilderImpl().setUserId(userId).setTariffId(Integer.parseInt(tariffId)).build());
            } else if(Objects.nonNull(tariffId) && user.getStatus()==0){
                request.setAttribute("subFail", true);
            }

            List<Tariff> tariffs = new ArrayList<>();
            if (Objects.nonNull(serviceId)) {
                tariffs = tariffService.getServiceTariff(Integer.parseInt(serviceId));
                if (Objects.nonNull(sortAction) && sortAction.equals("price"))
                    tariffs.sort(new Tariff.PriceComparator());
                if (Objects.nonNull(sortAction) && sortAction.equals("name"))
                    tariffs.sort(new Tariff.NameComparator());
                if (Objects.nonNull(sortAction) && sortAction.equals("nameR"))
                    tariffs.sort(new Tariff.NameReverseComparator());
            }

            if (Objects.nonNull(item)) {
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
