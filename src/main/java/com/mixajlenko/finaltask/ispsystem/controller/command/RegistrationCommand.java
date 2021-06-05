package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.finaltask.ispsystem.exception.*;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String role = request.getParameter("role");

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

            User user = new User(fName, sName, phone, email, 0, 0, password, 1);

            userService.add(user);
            if(user.getRole()==0){
                CommandUtil.goToPage(request, response, "/view/admin/userPageAdmin");
            }
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
        } catch (AlreadyExistUserException e) {
            request.setAttribute("existUser", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/registration.jsp");
        }
    }
}
