package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.finaltask.ispsystem.exception.*;
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

public class RegistrationCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);

    private static final String DEFAULT_PAGE = "/WEB-INF/view/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Start execution RegistrationCommand");

        var factory = ServiceFactory.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        try {
            if (Objects.isNull(email) && Objects.isNull(password)) {
                logger.info("email or password is null");
                throw new WrongDataException();
            }
            if (!ValidationData.isEmailValid(email)) {
                logger.info("email is not valid");
                throw new WrongDataException();
            }

            IUserService userService = factory.getUserService();
            if (Objects.nonNull(userService.getUserByEmail(email))) {
                logger.info("email already exist");
                throw new AlreadyExistUserException();
            }

            String fName = request.getParameter("firstName");
            String sName = request.getParameter("secondName");

            var user = new User.UserBuilderImpl()
                    .setFirstName(fName)
                    .setSecondName(sName)
                    .setPhone(phone)
                    .setEmail(email)
                    .setStatus(0)
                    .setWallet(0)
                    .setPassword(CommandUtil.encrypt(password, false).orElseThrow(PasswordGenerationException::new))
                    .setRole(1)
                    .build();

            userService.add(user);
            if (user.getRole() == 0) {
                logger.info("Go to userPageAdmin");
                CommandUtil.goToPage(request, response, "/view/admin/userPageAdmin");
            }
            logger.info("redirect to login page");
            response.sendRedirect("/");
        } catch (ServiceException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        } catch (WrongDataException e) {
            request.setAttribute("wrongData", false);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        } catch (NotFoundUserException e) {
            logger.error("Not found user");
        } catch (AlreadyExistUserException e) {
            request.setAttribute("existUser", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        } catch (PasswordGenerationException | NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
}
