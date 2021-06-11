package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.model.Service;
import com.mixajlenko.finaltask.ispsystem.service.IServiceService;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LoginCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Start execution login");
        var factory = ServiceFactory.getInstance();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            IUserService userService = factory.getUserService();
            IServiceService serviceService = factory.getServiceService();

            try {

                List<Service> services = serviceService.getAll();
                var user = userService.getUserByEmail(login);

                if (Objects.nonNull(user) && CommandUtil.verifyPass(password, user.getPassword())) {
                    req.getSession().setAttribute("user", user);
                    req.getSession().setAttribute("login1", login);
                    req.getSession().setAttribute("services", services);
                    req.getSession().setAttribute("globalUserId", user.getId());
                } else {
                    throw new NotFoundUserException();
                }
               String page = CommandUtil.getUserPageByRole(user.getRole());
                resp.sendRedirect(page);
            } catch (NotFoundUserException e) {
                req.setAttribute("notFound", true);
                CommandUtil.goToPage(req, resp, "/");
            } catch (SQLException | NamingException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
