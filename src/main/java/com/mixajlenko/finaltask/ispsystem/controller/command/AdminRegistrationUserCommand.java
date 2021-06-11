package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminRegistrationUserCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(AdminRegistrationUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start execution registration");

        var factory = ServiceFactory.getInstance();

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        try {

            if (Objects.isNull(email) && Objects.isNull(password)) {
                throw new WrongDataException();
            }

            if (!ValidationData.isEmailValid(email) || !ValidationData.isPasswordValid(password)) {
                logger.warn("WrongDataException thrown");
                throw new WrongDataException();
            }

            var userService = factory.getUserService();

            String fName = request.getParameter("fName");
            String sName = request.getParameter("sName");

            var user = new User.UserBuilderImpl()
                    .setFirstName(fName)
                    .setSecondName(sName)
                    .setPhone(phone)
                    .setEmail(email)
                    .setStatus(0)
                    .setWallet(0)
                    .setPassword(password)
                    .build();

            logger.info(user.toString());
            user.setRole(1);
            userService.add(user);
            logger.warn("go to page userPageAdmin");
            response.sendRedirect("/view/admin/userPageAdmin");

        } catch (ServiceException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageUsers.jsp");
        } catch (WrongDataException e) {
            logger.warn("Wrong data exception");
            request.setAttribute("wrongData", false);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageUsers.jsp");
        } catch (NotFoundUserException e) {
            logger.warn("Not found user exception");
        } catch (SQLException | NamingException | IOException throwables) {
            logger.warn(throwables.getMessage());
        }
    }
}
