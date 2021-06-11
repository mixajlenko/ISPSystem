package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SupportCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(SupportCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommandUtil.goToPage(request, response, "/WEB-INF/view/client/supportPage.jsp");
    }
}
