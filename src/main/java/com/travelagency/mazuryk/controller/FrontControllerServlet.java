package com.travelagency.mazuryk.controller;

import com.travelagency.mazuryk.commands.CommandResponse;
import com.travelagency.mazuryk.commands.FrontCommand;
import com.travelagency.mazuryk.db.enums.Target;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;



@WebServlet("/controller")
@MultipartConfig
public class FrontControllerServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FrontControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FrontCommand command = getCommand(request);
        command.init(getServletContext(),request,response);
        logger.info(()-> "New GET request" + command.getClass().getName());
        CommandResponse resp = command.process();
        String path = getPath(resp.getPath(),resp.getTarget());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(),req,resp);
        logger.info(()-> "New POST request" + command.getClass().getName());
        CommandResponse response = command.process();
        String path = getPath(response.getPath(),response.getTarget());
        resp.sendRedirect(path);
    }

    private String getPath(String path, Target target) {
        if(target == Target.COMMAND)
            return getCommandPath(path);
        else if(target == Target.JSP)
            return getJSPPath(path);
        else return getJSPPath("error");
    }

    private String getJSPPath(String target)  {
        return String.format("/jsp/%s.jsp",target);
    }
    private String getCommandPath(String target)  {
        return String.format("/controller?command=%s",target);
    }


    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format(
                    "com.travelagency.mazuryk.commands.%sCommand",
                    request.getParameter("command")));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
