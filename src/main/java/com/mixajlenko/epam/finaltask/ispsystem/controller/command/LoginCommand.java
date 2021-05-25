package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.IAccountService;
import com.mixajlenko.epam.finaltask.ispsystem.service.UserService;
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
            UserService userService = factory.getUserService();
            try {
                User user = userService.getByLoginAndPass(login, password);
                logger.info(user.toString());
                req.getSession().setAttribute("user", user);
                String page = CommandUtil.getUserPageByRole(user.getRoleId());
                if (user.getRoleId() == 0) {
                    IAccountService accountService = factory.getAccountService();
                    List<Account> accounts = accountService.getAll();
                    req.setAttribute("blocked", accountService.blockedAccounts(accounts));
                    req.setAttribute("active", accounts.size() - accountService.blockedAccounts(accounts));
                    req.setAttribute("users", accounts.size());
                }
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
