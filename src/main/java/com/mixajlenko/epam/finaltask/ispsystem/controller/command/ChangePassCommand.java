package com.mixajlenko.epam.finaltask.ispsystem.controller.command;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangePassCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        CommandUtil.goToPage(request, response, "/WEB-INF/view/client/changePassPage.jsp");
    }
}
