package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;

public class AdminManageTariffCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            ITariffService tariffService = factory.getTariffService();
            String serviceId = request.getParameter("id");

            Tariff tariff = tariffService.getById(Integer.valueOf(serviceId));

            request.setAttribute("id", tariff.getId());
            request.setAttribute("name", tariff.getName());
            request.setAttribute("description", tariff.getDescription());
            request.setAttribute("price", tariff.getPrice());

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");

            tariff.setName(name);
            tariff.setDescription(description);
            tariff.setPrice(Integer.parseInt(price));

            tariffService.update(tariff);

            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageTariff.jsp");
        } catch (NotFoundUserException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageTariff.jsp");
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
}

