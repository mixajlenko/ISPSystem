package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;


public class ManagePlanCommand implements ICommand {

    Logger logger = Logger.getLogger(ManagePlanCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("manage plan execute");
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            ITariffService tariffService = factory.getTariffService();
            String serviceId = request.getParameter("param");
            List<Tariff> tariffList = tariffService.getServiceTariff(Integer.parseInt(serviceId));
            request.setAttribute("tariffs", tariffList);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/managePlan.jsp");
        } catch (NotFoundUserException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/managePlan.jsp");
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
}
