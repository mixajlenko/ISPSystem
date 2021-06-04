package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationCommand implements ICommand {

    private static Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        logger.info("Start execution registration");

        ServiceFactory factory = ServiceFactory.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        try {
            if (Objects.isNull(email) && Objects.isNull(password)) {
                throw new WrongDataException();
            }
            if (!ValidationData.isEmailValid(email) || !ValidationData.isPasswordValid(password)) {
                throw new WrongDataException();
            }

            IUserService IUserService = factory.getUserService();

            String fName = request.getParameter("firstName");
            String sName = request.getParameter("secondName");

            User user = new User(fName, sName, phone, email, 0, 0, password, 1);

            IUserService.add(user);

//            user = IUserService.getByLoginAndPass(email, password);
//            request.getSession().setAttribute("user", user);
//            String page = CommandUtil.getUserPageByRole(1);

            CommandUtil.goToPage(request, response, "/");
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
