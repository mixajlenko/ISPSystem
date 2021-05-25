package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminUserCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        CommandUtil.goToPage(request, response, "/WEB-INF/view/admin/userPageAdmin.jsp");
    }
}
