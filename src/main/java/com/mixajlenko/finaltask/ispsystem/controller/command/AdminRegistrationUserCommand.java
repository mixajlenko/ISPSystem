package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.ServiceException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminRegistrationUserCommand implements ICommand{

    private static Logger logger = Logger.getLogger(AdminRegistrationUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

            User user = new User.UserBuilderImpl()
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
            IUserService.add(user);

            response.sendRedirect("/view/admin/userPageAdmin");

        } catch (ServiceException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageUsers.jsp");
        } catch (WrongDataException e) {
            request.setAttribute("wrongData", false);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/manageUsers.jsp");
        } catch (NotFoundUserException e) {
            logger.error("Not found user");
        } catch (SQLException | NamingException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
