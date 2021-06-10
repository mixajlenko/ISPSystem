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

    private static Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Start execution registration");

        ServiceFactory factory = ServiceFactory.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        try {
            if (Objects.isNull(email) && Objects.isNull(password)) {
                throw new WrongDataException();
            }
            if (!ValidationData.isEmailValid(email)) {
                throw new WrongDataException();
            }

            IUserService userService = factory.getUserService();
            if(Objects.nonNull(userService.getUserByEmail(email))){
                throw new AlreadyExistUserException();
            }

            String fName = request.getParameter("firstName");
            String sName = request.getParameter("secondName");

            User user = new User.UserBuilderImpl()
                    .setFirstName(fName)
                    .setSecondName(sName)
                    .setPhone(phone)
                    .setEmail(email)
                    .setStatus(0)
                    .setWallet(0)
                    .setPassword(CommandUtil.encrypt(password))
                    .setRole(1)
                    .build();

            userService.add(user);
            if(user.getRole()==0){
                CommandUtil.goToPage(request, response, "/view/admin/userPageAdmin");
            }
            response.sendRedirect("/");
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
        } catch (AlreadyExistUserException e) {
            request.setAttribute("existUser", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/registration.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
