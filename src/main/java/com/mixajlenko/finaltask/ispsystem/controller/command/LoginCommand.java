package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.model.Service;
import com.mixajlenko.finaltask.ispsystem.model.User;
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
    private static Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Start execution login");
        ServiceFactory factory = ServiceFactory.getInstance();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            IUserService userService = factory.getUserService();
            IServiceService serviceService = factory.getServiceService();

            try {
                List<Service> services = serviceService.getAll();
                User user = userService.getByLoginAndPass(login, CommandUtil.encrypt(password));
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("login1", login);
                req.getSession().setAttribute("services", services);
                req.getSession().setAttribute("globalUserId", user.getId());
                String page = CommandUtil.getUserPageByRole(user.getRole());
                resp.sendRedirect(page);
            } catch (NotFoundUserException e) {
                req.setAttribute("notFound", true);
                CommandUtil.goToPage(req, resp, "/");
            } catch (SQLException | NamingException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
