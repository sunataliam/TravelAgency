package com.travelagency.mazuryk.commands;

import com.travelagency.mazuryk.db.dao.DBException;
import com.travelagency.mazuryk.db.dao.UserDAO;
import com.travelagency.mazuryk.db.dao.impl.UserDAOImpl;
import com.travelagency.mazuryk.db.entity.User;
import com.travelagency.mazuryk.db.enums.Target;
import com.travelagency.mazuryk.db.service.UserService;
import com.travelagency.mazuryk.db.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand extends FrontCommand{

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public CommandResponse process() throws IOException {
        try {
            UserDAO userDAO = new UserDAOImpl();
            UserService userService = new UserServiceImpl();
            User user = userService.getUserFromRequest(request);
            if (user != null && userService.checkIfEmailUnique(user)){
                userDAO.create(user);
                request.getSession().setAttribute("currentUser", user);
                logger.info("New user registered");
                return new CommandResponse(Target.JSP,"userProfile");
            }
        } catch (DBException e) {
            logger.error("User registration error:", e);
        }
        return new CommandResponse(Target.JSP,"error");
    }
}
