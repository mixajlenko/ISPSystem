package com.mixajlenko.epam.finaltask.ispsystem.controller.command.commandfactory;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.*;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.Operation;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundOperationException;
import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Logger logger = Logger.getLogger(CommandFactory.class);
    private static final Map<String, ICommand> allKnownCommandMap = new HashMap<>();

    static {
        allKnownCommandMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandMap.put(Operation.REGISTRATION, new RegistrationCommand());
        allKnownCommandMap.put(Operation.LOGOUT, new LogoutCommand());
        allKnownCommandMap.put(Operation.ADMIN_MENU, new AdminMenuCommand());
        allKnownCommandMap.put(Operation.ADMIN_SERVICES, new AdminServiceCommand());
        allKnownCommandMap.put(Operation.CLIENT_MENU, new ClientMenuCommand());
        allKnownCommandMap.put(Operation.ADMIN_MANAGE_PLAN, new ManagePlanCommand());
        allKnownCommandMap.put(Operation.CLIENT_MANAGE_PROFILE, new ClientManageProfileCommand());
        allKnownCommandMap.put(Operation.CLIENT_SERVICES, new ClientServiceCommand());
        allKnownCommandMap.put(Operation.PAYMENT_SYSTEM_PAGE, new PaymentSysCommand());
        allKnownCommandMap.put(Operation.SUPPORT_PAGE, new SupportCommand());
        allKnownCommandMap.put(Operation.ADMIN_USERS, new AdminUserCommand());
        allKnownCommandMap.put(Operation.ADMIN_MANAGE_TARIFF, new AdminAddTariffCommand());
        allKnownCommandMap.put(Operation.ADMIN_MANAGE_USER, new AdminRegistrationUserCommand());
    }


    public static ICommand getCommand(String url) throws NotFoundOperationException {
        ICommand command = allKnownCommandMap.get(url);
        logger.info(url);
        if (command == null) {
            throw new NotFoundOperationException();
        }

        return command;
    }
}
