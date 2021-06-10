package com.mixajlenko.finaltask.ispsystem.controller.filter;

import javax.servlet.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class LanguageFilter implements Filter {

    private static Logger logger = Logger.getLogger(LanguageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        logger.info("TEST");
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI();
        path = path.replaceAll("language/", "");
        if (path.equals("/view//")) {
            path = "/";
        }

        logger.info("uri" + req.getRequestURI());
        logger.info("path" + path);

        String language = req.getParameter("language");
        boolean isEnglish = language.equals("EN");
        boolean isRussian = language.equals("RU");

        if (isEnglish) {
            req.getSession().setAttribute("language", "en-EN");
        } else if (isRussian) {
            req.getSession().setAttribute("language", "ru-RU");
        }

        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
