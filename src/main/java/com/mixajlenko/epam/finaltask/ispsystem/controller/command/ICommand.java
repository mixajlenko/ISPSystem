package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ICommand {

    void execute(HttpServletRequest request, HttpServletResponse response);

}
