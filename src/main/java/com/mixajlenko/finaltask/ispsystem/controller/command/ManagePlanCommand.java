package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


public class ManagePlanCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(ManagePlanCommand.class);

    private static final String COMMAND_INTERFACE = "commandInterface";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start execution ManagePlanCommand");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String redirect = request.getParameter("redirect");

        try {
            var factory = ServiceFactory.getInstance();
            ITariffService tariffService = factory.getTariffService();
            String serviceId = request.getParameter("param");
            String command = request.getParameter("command");
            request.setAttribute(COMMAND_INTERFACE, false);

            if (Objects.isNull(serviceId)) {
                serviceId = request.getParameter("id");
            }
            if (Objects.nonNull(command)) {
                if (Objects.equals("update",command))
                    request.setAttribute(COMMAND_INTERFACE, true);
                var id = Integer.parseInt(request.getParameter("id"));
                var tariff = tariffService.getById(id);
                switch (command) {
                    case "delete":
                        tariffService.delete(id);
                        break;
                    case "updateCommand":
                        tariff.setName(name);
                        tariff.setDescription(description);
                        tariff.setPrice(Integer.parseInt(price));
                        tariffService.update(tariff);
                        request.setAttribute(COMMAND_INTERFACE, false);
                        break;
                    default:
                }
                logger.info(command + " execute");
            }
            List<Tariff> tariffList = tariffService.getServiceTariff(Integer.parseInt(serviceId));
            request.setAttribute("tariffs", tariffList);
            if (Objects.equals("true", redirect)) {
                logger.info("Redirection to managePlan");
                response.sendRedirect("/view/admin/managePlan?param=" + serviceId);
            } else {
                logger.info("Go to to managePlan");
                CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/managePlan.jsp");
            }
        } catch (NotFoundUserException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/managePlan.jsp");
        } catch (SQLException | NamingException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
