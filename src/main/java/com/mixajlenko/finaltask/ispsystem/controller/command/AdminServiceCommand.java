package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Service;
import com.mixajlenko.finaltask.ispsystem.service.IServiceService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory factory = ServiceFactory.getInstance();
        try {
            IServiceService serviceService = factory.getServiceService();
            List<Service> services = serviceService.getAll();
            request.setAttribute("services", services);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/servicePageAdmin.jsp");
    }
}
