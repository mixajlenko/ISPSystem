package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.ValidationData;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.model.User;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Objects;

public class ClientManageProfileCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        try {
            IUserService userService = serviceFactory.getUserService();
            String change = request.getParameter("change");
            if(Objects.nonNull(change)){
                switch (change){
                    case "name" :
                        request.setAttribute("nameChange", true);
                        break;
                    case "phone":
                        request.setAttribute("phoneChange", true);
                        break;
                    case "email":
                        request.setAttribute("emailChange", true);
                        break;
                    case "password":
                        request.setAttribute("passChange", true);
                        break;
                    default:
                }
            }
            User user = userService.getUserByEmail(String.valueOf(request.getSession().getAttribute("login1")));

            String firstName = request.getParameter("fName");
            String secondName = request.getParameter("lName");
            String nameAction = request.getParameter("changeName");
            String email = request.getParameter("email");
            String emailAction = request.getParameter("changeEmail");
            String phone = request.getParameter("phone");
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
                    && Objects.nonNull(passAction)
                    && CommandUtil.encrypt(oldPass).equals(user.getPassword())
                    && ValidationData.isPasswordValid(newPass)) {
                user.setPassword(CommandUtil.encrypt(newPass));
                userService.update(user);
            }

            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getSecondName());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("phone", user.getPhone());

            CommandUtil.goToPage(request, response, "/WEB-INF/view/client/profile.jsp");
        } catch (NotFoundUserException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/client/profile.jsp");
        } catch (WrongDataException e) {
            request.setAttribute("wrongData", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/client/profile.jsp");


        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }

    }
}
