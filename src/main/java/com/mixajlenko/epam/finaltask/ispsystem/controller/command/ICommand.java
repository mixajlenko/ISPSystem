package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {

    void execute(HttpServletRequest request, HttpServletResponse response);

}
