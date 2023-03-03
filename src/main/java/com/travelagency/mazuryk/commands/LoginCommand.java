package com.travelagency.mazuryk.commands;

import com.travelagency.mazuryk.db.entity.User;
import com.travelagency.mazuryk.db.enums.Target;
import com.travelagency.mazuryk.db.service.UserService;
import com.travelagency.mazuryk.db.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand extends FrontCommand{

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public CommandResponse process() throws  IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UserService userService = new UserServiceImpl();
            User user = userService.login(email, password);
            if(user != null){
                request.getSession().setAttribute("currentUser", user);
                switch (user.getRole()) {
                    case USER:
                        logger.info("Logged new user");
                        return new CommandResponse(Target.JSP,"userProfile");
                    case MANAGER:
                        logger.info("Logged new manager");
                        return new CommandResponse(Target.JSP,"managerProfile");
                    case ADMIN:
                        logger.info("Logged new admin");
                        return new CommandResponse(Target.JSP,"adminProfile");
                }
            }
        } catch (SQLException e) {
            logger.error("User login error:", e);
        }
        return new CommandResponse(Target.JSP,"error");
    }
}
