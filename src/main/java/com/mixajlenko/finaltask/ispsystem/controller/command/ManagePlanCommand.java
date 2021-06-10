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


public class ManagePlanCommand implements ICommand {

    Logger logger = Logger.getLogger(ManagePlanCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("manage plan execute");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String redirect = request.getParameter("redirect");
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            ITariffService tariffService = factory.getTariffService();
            String serviceId = request.getParameter("param");
            String command = request.getParameter("command");
            request.setAttribute("commandInterface", false);

            if (serviceId == null) {
                serviceId = request.getParameter("id");
            }
            if (command != null) {
                if(command.equals("update"))
                    request.setAttribute("commandInterface", true);
                int id = Integer.parseInt(request.getParameter("id"));
                Tariff tariff = tariffService.getById(id);
                switch (command) {
                    case "delete":
                        tariffService.delete(id);
                        break;
                    case "updateCommand":
                        tariff.setName(name);
                        tariff.setDescription(description);
                        tariff.setPrice(Integer.parseInt(price));
                        tariffService.update(tariff);
                        request.setAttribute("commandInterface", false);
                        break;
                    default:
                }
            }
            List<Tariff> tariffList = tariffService.getServiceTariff(Integer.parseInt(serviceId));
            request.setAttribute("tariffs", tariffList);
            if ("true".equals(redirect)){
                System.out.println(serviceId);
                response.sendRedirect("/view/admin/managePlan?param=" + serviceId);
            } else {
                CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/managePlan.jsp");
            }
        } catch (NotFoundUserException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/managePlan.jsp");
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
