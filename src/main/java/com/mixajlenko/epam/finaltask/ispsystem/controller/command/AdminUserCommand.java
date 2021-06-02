package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AdminUserCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory factory = ServiceFactory.getInstance();

        try {
            IUserService userService = factory.getUserService();
            String command = request.getParameter("command");
            System.out.println(command);
            if(command!=null){
                int id = Integer.parseInt(request.getParameter("id"));
                System.out.println(id);
                User user = userService.getById(id);
                switch (command){
                    case "delete":
                        userService.delete(id);
                        break;
                    case "unlock":
                        user.setStatus(1);
                        System.out.println(user);
                        userService.update(user);
                        break;
                    case "block":
                        user.setStatus(0);
                        userService.update(user);
                        break;
                    default:
                }
            }

            List<User> users = userService.getAll();
            request.setAttribute("users", users);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/userPageAdmin.jsp");
    }
}
