package com.mixajlenko.finaltask.ispsystem.controller.command.commandfactory;

import com.mixajlenko.finaltask.ispsystem.controller.command.*;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.Operation;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundOperationException;
import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class);
    private static final Map<String, ICommand> allKnownCommandMap = new HashMap<>();

    static {
        allKnownCommandMap.put(Operation.LOGIN.getCommand(), new LoginCommand());
        allKnownCommandMap.put(Operation.REGISTRATION.getCommand(), new RegistrationCommand());
        allKnownCommandMap.put(Operation.LOGOUT.getCommand(), new LogoutCommand());
        allKnownCommandMap.put(Operation.ADMIN_MENU.getCommand(), new AdminMenuCommand());
        allKnownCommandMap.put(Operation.ADMIN_SERVICES.getCommand(), new AdminServiceCommand());
        allKnownCommandMap.put(Operation.CLIENT_MENU.getCommand(), new ClientMenuCommand());
        allKnownCommandMap.put(Operation.ADMIN_MANAGE_PLAN.getCommand(), new ManagePlanCommand());
        allKnownCommandMap.put(Operation.CLIENT_MANAGE_PROFILE.getCommand(), new ClientManageProfileCommand());
        allKnownCommandMap.put(Operation.CLIENT_SERVICES.getCommand(), new ClientServiceCommand());
        allKnownCommandMap.put(Operation.PAYMENT_SYSTEM_PAGE.getCommand(), new PaymentSysCommand());
        allKnownCommandMap.put(Operation.SUPPORT_PAGE.getCommand(), new SupportCommand());
        allKnownCommandMap.put(Operation.ADMIN_USERS.getCommand(), new AdminUserCommand());
        allKnownCommandMap.put(Operation.ADMIN_MANAGE_TARIFF.getCommand(), new AdminAddTariffCommand());
        allKnownCommandMap.put(Operation.ADMIN_MANAGE_USER.getCommand(), new AdminRegistrationUserCommand());
        allKnownCommandMap.put(Operation.DOWNLOAD_SERVICES.getCommand(), new DownloadServiceCommand());
    }

    private CommandFactory() {
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
