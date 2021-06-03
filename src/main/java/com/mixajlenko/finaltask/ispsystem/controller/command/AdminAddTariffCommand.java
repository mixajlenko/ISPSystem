package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminAddTariffCommand implements ICommand {

    private static Logger logger = Logger.getLogger(AdminAddTariffCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory factory = ServiceFactory.getInstance();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String serviceId = request.getParameter("param");


        System.out.println(name);
        System.out.println(description);
        System.out.println(price);
        System.out.println(serviceId);
        try {

            if (Objects.isNull(name) && Objects.isNull(description) && Objects.isNull(price)) {
                logger.info("error1");
                throw new WrongDataException();
            }

            ITariffService tariffService = factory.getTariffService();

            Tariff tariff = new Tariff(name, description, Integer.parseInt(price));
            System.out.println(tariff);

            boolean test = tariffService.add(tariff);
            System.out.println(test);

            int id = tariffService.getByName(name).getId();
            System.out.println(id);
            System.out.println(serviceId);
            tariffService.setServiceTariff(Integer.parseInt(serviceId), id);
            CommandUtil.goToPage(request, response, "/view/admin/managePlan?param="+serviceId);

        } catch (ServiceException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageTariff.jsp");
        } catch (WrongDataException | SQLException | NamingException e) {
            request.setAttribute("wrongData", false);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageTariff.jsp");
        }
    }
}

