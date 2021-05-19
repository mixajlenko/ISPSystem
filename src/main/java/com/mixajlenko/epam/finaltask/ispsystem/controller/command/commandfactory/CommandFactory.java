package com.mixajlenko.epam.finaltask.ispsystem.controller.command.commandfactory;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.ICommand;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.LoginCommand;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.LogoutCommand;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.RegistrationCommand;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.Operation;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundOperationException;


import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Map<String, ICommand> allKnownCommandMap = new HashMap<>();

    static {
        allKnownCommandMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandMap.put(Operation.REGISTRATION, new RegistrationCommand());
        allKnownCommandMap.put(Operation.LOGOUT, new LogoutCommand());
    }


    public static ICommand getCommand(String url) throws NotFoundOperationException {
        ICommand command = allKnownCommandMap.get(url);

        if (command == null) {
            throw new NotFoundOperationException();
        }

        return command;
    }
}
