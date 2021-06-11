package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminAddTariffCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminAddTariffCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("AdminAddTariffCommand start");
        var factory = ServiceFactory.getInstance();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String serviceId = request.getParameter("param");
        String redirect = request.getParameter("redirect");

        try {

            if (Objects.isNull(name) && Objects.isNull(description) && Objects.isNull(price)) {
                logger.info("Name, Description or Price is Null");
                throw new WrongDataException();
            }

            var tariffService = factory.getTariffService();

            tariffService.add(new Tariff.TariffBuilderImpl().setName(name).setDescription(description).setPrice(Integer.parseInt(price)).build());
            tariffService.setServiceTariff(Integer.parseInt(serviceId), tariffService.getByName(name).getId());

            if ("true".equals(redirect)) {
                logger.info("redirection");
                response.sendRedirect("/view/admin/managePlan?param=" + serviceId);
            } else {
                logger.info("Go to page managePlan");
                CommandUtil.goToPage(request, response, "/view/admin/managePlan?param=" + serviceId);
            }

        } catch (ServiceException e) {
            logger.info("notFound exception");
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageTariff.jsp");
        } catch (WrongDataException | SQLException | NamingException e) {
            logger.info("wrongData exception");
            request.setAttribute("wrongData", false);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageTariff.jsp");
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }
}

