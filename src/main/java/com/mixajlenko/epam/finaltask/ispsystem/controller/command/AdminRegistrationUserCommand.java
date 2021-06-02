package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminRegistrationUserCommand implements ICommand{

    private static Logger logger = Logger.getLogger(AdminRegistrationUserCommand.class);

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

            IUserService IUserService = factory.getUserService();

            String fName = request.getParameter("fName");
            String sName = request.getParameter("sName");

            User user = new User(fName, sName, phone, email, 0,0,password);

            logger.info(user.toString());
            user.setRole(1);
            IUserService.add(user);

            CommandUtil.goToPage(request, response, "/view/admin/userPageAdmin");

        } catch (ServiceException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageUsers.jsp");
        } catch (WrongDataException e) {
            request.setAttribute("wrongData", false);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageUsers.jsp");
        } catch (NotFoundUserException e) {
            logger.error("Not found user");
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
}
