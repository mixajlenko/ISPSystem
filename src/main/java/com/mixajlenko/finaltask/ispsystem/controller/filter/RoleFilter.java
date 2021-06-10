package com.mixajlenko.finaltask.ispsystem.controller.filter;


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

    private static Logger logger = Logger.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");


        if (Objects.nonNull(user)) {
            int accessLevel = user.getRole();
            if (accessLevel == Role.ADMIN.getAccessLevel()) {
                filterChain.doFilter(req, resp);
            } else {
                req.getRequestDispatcher("/view/client/mainPageUser").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
