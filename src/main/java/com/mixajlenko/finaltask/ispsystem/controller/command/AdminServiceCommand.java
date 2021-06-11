package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Service;
import com.mixajlenko.finaltask.ispsystem.service.IServiceService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminServiceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start execution AdminServiceCommand");
        var factory = ServiceFactory.getInstance();
        try {
            IServiceService serviceService = factory.getServiceService();
            List<Service> services = serviceService.getAll();
            request.setAttribute("services", services);
        } catch (NamingException | SQLException e) {
            logger.warn(e.getMessage());
        }
        CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/servicePageAdmin.jsp");
    }
}
