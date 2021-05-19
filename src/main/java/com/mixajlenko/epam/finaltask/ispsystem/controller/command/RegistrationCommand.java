package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.IAccountService;
import com.mixajlenko.epam.finaltask.ispsystem.service.UserService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrationCommand implements ICommand {

    private static Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info("Start execution registration");

        ServiceFactory factory = ServiceFactory.getInstance();

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        logger.info("email =" + email);
        logger.info("phone =" + phone);
        logger.info("password =" + password);

        try {

            if (Objects.isNull(email) && Objects.isNull(password)) {
                logger.info("error1");
                throw new WrongDataException();
            }

            if (!ValidationData.isEmailValid(email) || !ValidationData.isPasswordValid(password)) {
                logger.info("error2");
                throw new WrongDataException();
            }

            UserService userService = factory.getUserService();
            IAccountService accountService = factory.getAccountService();

            String fName = request.getParameter("firstName");
            String sName = request.getParameter("secondName");

            logger.info("fName =" + fName);
            logger.info("sName =" + sName);

            User user = new User(fName, sName, phone, email);

            logger.info(user.toString());
            user.setRoleId(1);
            userService.add(user);
            Account account = new Account(userService.getUserByEmail(email).getId(),0,0,password,user.getRoleId());
            accountService.add(account);
            user = userService.getByLoginAndPass(email, password);

            request.getSession().setAttribute("user", user);

            String page = CommandUtil.getUserPageByRole(1);

            CommandUtil.goToPage(request, response, page);

        } catch (ServiceException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/registration.jsp");
        } catch (WrongDataException e) {
            request.setAttribute("wrongData", false);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/registration.jsp");
        } catch (NotFoundUserException e) {
            logger.error("Not found user");
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
}
