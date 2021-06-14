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
import java.util.stream.Collectors;

public class ClientServiceCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(ClientServiceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start execution ClientServiceCommand");
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
            if (Objects.nonNull(tariffId) && userId != 0 && user.getStatus() == 1) {
                if((userTariffService.getUserTariffByUserId(user.getId()).stream().anyMatch(t -> t.getTariffId() == Integer.parseInt(tariffId)))){
                    request.setAttribute("alreadyExistTariff", true);
                    request.setAttribute("subSuccess", false);
                } else {
                    userTariffService.add(new UserTariff.UserTariffBuilderImpl().setUserId(userId).setTariffId(Integer.parseInt(tariffId)).build());
                    request.setAttribute("subSuccess", true);
                    logger.info("Subscription accepted");
                }
            } else if (Objects.nonNull(tariffId) && user.getStatus() == 0) {
                request.setAttribute("subFail", true);
                logger.info("Subscription failed");
            }

            List<Tariff> tariffs = new ArrayList<>();
            if (Objects.nonNull(serviceId)) {
                tariffs = tariffService.getServiceTariff(Integer.parseInt(serviceId));
                if (Objects.nonNull(sortAction)) {
                    switch (sortAction) {
                        case "price":
                            tariffs.sort(new Tariff.PriceComparator());
                            break;
                        case "name":
                            tariffs.sort(new Tariff.NameComparator());
                            break;
                        case "nameR":
                            tariffs.sort(new Tariff.NameReverseComparator());
                            break;
                        default:
                    }
                }
                logger.info("Sorting by" + sortAction);
            }

            if (Objects.nonNull(item)) {
                logger.info("List of tariffs showed for Admin");
                request.setAttribute("showTariffs", true);
            }

            List<Service> services = serviceService.getAll();
            List<Tariff> complex = tariffService.getAll().stream().filter(t -> t.getName().contains("Plan")).collect(Collectors.toList());

            request.setAttribute("services", services);
            request.setAttribute("item", item);
            request.setAttribute("paramId", serviceId);

            request.setAttribute("tariffs", tariffs);
            request.setAttribute("complex", complex);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        logger.info("go to servicePage");
        CommandUtil.goToPage(request, response, "/WEB-INF/view/client/servicePage.jsp");
    }
}
