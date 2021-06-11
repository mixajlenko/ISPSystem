package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundUserException;
import com.mixajlenko.finaltask.ispsystem.exception.WrongDataException;
import com.mixajlenko.finaltask.ispsystem.model.Payment;
import com.mixajlenko.finaltask.ispsystem.service.IPaymentService;
import com.mixajlenko.finaltask.ispsystem.service.IUserService;
import com.mixajlenko.finaltask.ispsystem.service.IUserTariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PaymentSysCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(PaymentSysCommand.class);

    private static final String DEFAULT_PAGE = "/WEB-INF/view/client/paymentSystemPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var serviceFactory = ServiceFactory.getInstance();

        try {

            IUserService userService = serviceFactory.getUserService();
            IPaymentService paymentService = serviceFactory.getPaymentService();
            IUserTariffService userTariffService = serviceFactory.getUserTariffService();

            var user = userService.getUserByEmail(String.valueOf(request.getSession().getAttribute("login1")));

            String payCommand = request.getParameter("pay");
            String amount = request.getParameter("amount");
            String payForTariff = request.getParameter("command");
            String tariffId = request.getParameter("id");
            String tariffPrice = request.getParameter("price");

            String redirect = request.getParameter("redirect");

            if (Objects.nonNull(payForTariff) && Objects.equals("payForTariff",payForTariff) && Objects.nonNull(tariffId) && Objects.nonNull(tariffPrice)) {
                if (user.getWallet() - Integer.parseInt(tariffPrice) >= 0) {
                    if (user.getStatus() == 0) {
                        user.setStatus(1);
                        userService.update(user);
                    }
                    var userTariff = userTariffService.getUserTariffByTariffIdUserId(Integer.parseInt(tariffId), user.getId());
                    userTariff.setStatus(1);
                    userTariff.setNextBill(CommandUtil.getNextBill());
                    userTariffService.update(userTariff);
                    user.setWallet(user.getWallet() - Integer.parseInt(tariffPrice));
                    userService.update(user);
                    var payment = new Payment.PaymentsBuilderImpl()
                            .setUserId(user.getId())
                            .setBill(Integer.parseInt(tariffPrice))
                            .setStatus(0)
                            .setBalance(user.getWallet())
                            .setDate(CommandUtil.getDate())
                            .setType("Pay for service")
                            .build();
                    paymentService.add(payment);
                } else {
                    var payment = new Payment.PaymentsBuilderImpl()
                            .setUserId(user.getId())
                            .setBill(Integer.parseInt(tariffPrice))
                            .setStatus(1)
                            .setBalance(user.getWallet())
                            .setDate(CommandUtil.getDate())
                            .setType("Pay for service")
                            .build();
                    paymentService.add(payment);
                }
                response.sendRedirect("/view/client/mainPageUser");
                return;
            }
            if (Objects.nonNull(payCommand) && Objects.nonNull(amount)) {
                if (userTariffService.getAllUserTariffByUserId(user.getId()).isEmpty()) {
                    user.setStatus(1);
                    userService.update(user);
                }
                paymentService.add(new Payment.PaymentsBuilderImpl()
                        .setUserId(user.getId())
                        .setBill(Integer.parseInt(amount))
                        .setStatus(0)
                        .setBalance(user.getWallet() + Integer.parseInt(amount))
                        .setDate(CommandUtil.getDate())
                        .setType("Refund")
                        .build());
                user.setWallet(user.getWallet() + Integer.parseInt(amount));
                userService.update(user);
            }
            List<Payment> payments = paymentService.getAll().stream().filter(p -> p.getUserId() == user.getId()).collect(Collectors.toList());

            request.setAttribute("paymentHistory", payments);
            request.setAttribute("fund", user.getWallet());

            if (!"true".equals(redirect)) {
                CommandUtil.goToPage(request, response, DEFAULT_PAGE);
            } else {
                response.sendRedirect("/view/client/paymentSystemPage");
            }
        } catch (NotFoundUserException e) {
            request.setAttribute("notFound", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        } catch (WrongDataException e) {
            request.setAttribute("wrongData", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);


        } catch (SQLException | NamingException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
