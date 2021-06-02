package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.epam.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.epam.finaltask.ispsystem.model.Payment;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.model.User;
import com.mixajlenko.epam.finaltask.ispsystem.model.UserTariff;
import com.mixajlenko.epam.finaltask.ispsystem.service.IPaymentService;
import com.mixajlenko.epam.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.epam.finaltask.ispsystem.service.IUserTariffService;
import com.mixajlenko.epam.finaltask.ispsystem.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PaymentSysCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        try {

            IUserService userService = serviceFactory.getUserService();
            IPaymentService paymentService = serviceFactory.getPaymentService();
            ITariffService tariffService = serviceFactory.getTariffService();
            IUserTariffService userTariffService = serviceFactory.getUserTariffService();

            User user = userService.getUserByEmail(String.valueOf(request.getSession().getAttribute("login1")));

            String payCommand = request.getParameter("pay");
            String amount = request.getParameter("amount");
            String payForTariff = request.getParameter("command");
            String tariffId = request.getParameter("id");
            String tariffPrice = request.getParameter("price");


            if (Objects.nonNull(payForTariff) && Objects.nonNull(tariffId) && Objects.nonNull(tariffPrice)) {

                if (user.getWallet() - Integer.parseInt(tariffPrice) >= 0) {
                    UserTariff userTariff = userTariffService.getUserTariffByTariffIdUserId(Integer.parseInt(tariffId),user.getId());
                    userTariff.setStatus(1);
                    userTariff.setNextBill(CommandUtil.getNextBill());
                    userTariffService.update(userTariff);
                    user.setWallet(user.getWallet() - Integer.parseInt(tariffPrice));
                    userService.update(user);
                    paymentService.add(new Payment(user.getId(), Integer.parseInt(tariffPrice), 0, user.getWallet() - Integer.parseInt(tariffPrice), CommandUtil.getDate()));
                } else {
                    Payment payment = new Payment(user.getId(), Integer.parseInt(tariffPrice), 1, user.getWallet(), CommandUtil.getDate());
                    paymentService.add(payment);
                }
                CommandUtil.goToPage(request, response, "/view/client/mainPageUser");
            }

            if (Objects.nonNull(payCommand) && Objects.nonNull(amount)) {
                Payment payment = new Payment(user.getId(), Integer.parseInt(amount), 0, user.getWallet() + Integer.parseInt(amount), CommandUtil.getDate());
                paymentService.add(payment);
                user.setWallet(user.getWallet() + Integer.parseInt(amount));
                userService.update(user);
            }

            List<Payment> payments = paymentService.getAll().stream().filter(p -> p.getUserId() == user.getId()).collect(Collectors.toList());

            request.setAttribute("paymentHistory", payments);
            request.setAttribute("fund", user.getWallet());
            CommandUtil.goToPage(request, response, "/WEB-INF/view/client/paymentSystemPage.jsp");
        } catch (NotFoundUserException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/client/paymentSystemPage.jsp");
        } catch (WrongDataException e) {
            request.setAttribute("wrongData", true);
            CommandUtil.goToPage(request, response, "/WEB-INF/view/client/paymentSystemPage.jsp");


        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
}
