package com.mixajlenko.epam.finaltask.ispsystem.controller.servlet;

import com.mixajlenko.epam.finaltask.ispsystem.controller.command.ICommand;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.commandfactory.CommandFactory;
import com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.epam.finaltask.ispsystem.exception.NotFoundOperationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType ("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI();
        path = path.substring(path.indexOf("view") - 1);
        ICommand command = null;
        try {
            command = CommandFactory.getCommand(path);
            command.execute(req, resp);
        } catch (NotFoundOperationException e) {
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/errorPage.jsp");
        }
    }

    //
//	private static final Logger log = Logger.getLogger(Controller.class);
//
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		process(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		process(request, response);
//	}
//
//	/**
//	 * Main method of this controller.
//	 */
//	private void process(HttpServletRequest request,
//			HttpServletResponse response) throws IOException, ServletException {
//
//		log.debug("Controller starts");
//
//		// extract command name from the request
//		String commandName = request.getParameter("command");
//		log.trace("Request parameter: command --> " + commandName);
//
//		// obtain command object by its name
//		ICommand command = CommandContainer.get(commandName);
//		log.trace("Obtained command --> " + command);
//
//		// execute command and get forward address
//		String forward = command.execute(request, response);
//		log.trace("Forward address --> " + forward);
//
//		log.debug("Controller finished, now go to forward address --> " + forward);
//
//		// if the forward address is not null go to the address
//		if (forward != null) {
//			RequestDispatcher disp = request.getRequestDispatcher(forward);
//			disp.forward(request, response);
//		}
//	}

}