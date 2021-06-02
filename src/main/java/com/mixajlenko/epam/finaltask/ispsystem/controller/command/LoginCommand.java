package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.IServiceService;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LoginCommand implements ICommand {
    private static Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Start execution login");
        ServiceFactory factory = ServiceFactory.getInstance();


        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            IUserService userService = factory.getUserService();
            IServiceService serviceService = factory.getServiceService();

            try {
                List<Service> services = serviceService.getAll();
                User user = userService.getByLoginAndPass(login, password);
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("login1", login);
                req.getSession().setAttribute("services", services);
                String page = CommandUtil.getUserPageByRole(user.getRole());
                CommandUtil.goToPage(req, resp, page);
            } catch (NotFoundUserException e) {
                req.setAttribute("notFound", true);
                CommandUtil.goToPage(req, resp, "/");
            } catch (WrongDataException e) {
                req.setAttribute("wrongData", true);
                CommandUtil.goToPage(req, resp, "/");
            } catch (SQLException | NamingException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
