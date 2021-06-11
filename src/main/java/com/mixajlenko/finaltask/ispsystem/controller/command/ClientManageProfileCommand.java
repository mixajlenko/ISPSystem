package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.PasswordGenerationException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ClientManageProfileCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(ClientManageProfileCommand.class);

    private static final String DEFAULT_PAGE = "/WEB-INF/view/client/profile.jsp";
    private static final String PHONE = "phone";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Start execution ClientManageProfileCommand");
        var serviceFactory = ServiceFactory.getInstance();

        try {
            IUserService userService = serviceFactory.getUserService();
            String change = request.getParameter("change");
            String isSuccess = request.getParameter("success");
            if (Objects.nonNull(isSuccess) && Objects.equals("true", isSuccess)) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("success", false);
            }

            if (Objects.nonNull(change)) {
                switch (change) {
                    case NAME:
                        logger.info("Name change selected");
                        request.setAttribute("nameChange", true);
                        break;
                    case PHONE:
                        logger.info("Phone change selected");
                        request.setAttribute("phoneChange", true);
                        break;
                    case EMAIL:
                        logger.info("Email change selected");
                        request.setAttribute("emailChange", true);
                        break;
                    case PASSWORD:
                        logger.info("Password change selected");
                        request.setAttribute("passChange", true);
                        break;
                    default:
                }
            }
            var user = userService.getUserByEmail(String.valueOf(request.getSession().getAttribute("login1")));

            String firstName = request.getParameter("fName");
            String secondName = request.getParameter("lName");
            String nameAction = request.getParameter("changeName");
            String email = request.getParameter(EMAIL);
            String emailAction = request.getParameter("changeEmail");
            String phone = request.getParameter(PHONE);
            String phoneAction = request.getParameter("changePhone");
            String oldPass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPass");
            String passAction = request.getParameter("changePassword");

            if (Objects.nonNull(firstName) && Objects.nonNull(secondName) && Objects.nonNull(nameAction)) {
                user.setFirstName(firstName);
                user.setSecondName(secondName);
                userService.update(user);
            }

            if (Objects.nonNull(email) && Objects.nonNull(emailAction) && ValidationData.isEmailValid(email)) {
                user.setEmail(email);
                userService.update(user);
                request.getSession().setAttribute("login1", email);
            }

            if (Objects.nonNull(phone) && Objects.nonNull(phoneAction)) {
                user.setPhone(phone);
                userService.update(user);
            }

            if (Objects.nonNull(oldPass)
                    && Objects.nonNull(newPass)
                    && Objects.nonNull(passAction)) {
                if (CommandUtil.verifyPass(oldPass, user.getPassword())
                        && ValidationData.isPasswordValid(newPass)) {
                    user.setPassword(CommandUtil.encrypt(newPass, false).orElseThrow(PasswordGenerationException::new));
                    userService.update(user);
                } else {
                    response.sendRedirect("/view/client/profile?success=false");
                    return;
                }
            }
            logger.info("User was updated");

            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getSecondName());
            request.setAttribute(EMAIL, user.getEmail());
            request.setAttribute(PHONE, user.getPhone());
            String redirect = request.getParameter("redirect");
            if (!Objects.equals("true", redirect)) {
                logger.info("got to profile page");
                CommandUtil.goToPage(request, response, DEFAULT_PAGE);
            } else {
                logger.info("redirect to profile page");
                response.sendRedirect("/view/client/profile?success=true");
            }

        } catch (NotFoundUserException e) {
            logger.info("Not Found Exception");
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        } catch (WrongDataException e) {
            logger.info("Wrong Data Exception");
            request.setAttribute("wrongData", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);


        } catch (SQLException | NamingException | PasswordGenerationException | IOException throwables) {
            logger.info(throwables.getMessage());
        }
    }
}
