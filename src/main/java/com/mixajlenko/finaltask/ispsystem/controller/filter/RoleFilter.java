package com.mixajlenko.finaltask.ispsystem.controller.filter;


import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.model.Role;
import com.mixajlenko.finaltask.ispsystem.model.User;
import javax.servlet.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class RoleFilter implements Filter {

    private static final Logger logger = Logger.getLogger(RoleFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        logger.info("Start execution RoleFilter");

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);

        var user = (User) session.getAttribute("user");


        if (Objects.nonNull(user)) {
            int accessLevel = user.getRole();
            if (accessLevel == Role.ADMIN.getAccessLevel()) {
                filterChain.doFilter(req, resp);
            } else {
                logger.info("Go to mainPageUser page");
                CommandUtil.goToPage(req, resp, "/view/client/mainPageUser");
               // req.getRequestDispatcher("/view/client/mainPageUser").forward(req, resp);
            }
        } else {
            logger.info("Go to login page");
            CommandUtil.goToPage(req, resp, "/");
            //req.getRequestDispatcher("/").forward(req, resp);
        }
    }

}
