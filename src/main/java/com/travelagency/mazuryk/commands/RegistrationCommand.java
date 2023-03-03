package com.travelagency.mazuryk.commands;

/*public class RegistrationCommand extends FrontCommand{

    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            User user = getNewUserFromRequest(request);
            userDAO.create(user);
            logger.info("A new user is registered");
            request.getSession().setAttribute("currentUser",user);
            return new CommandResponse(Target.JSP,FrontConstant.PROFILE_USER);

        }catch (MySQLException e) {
            logger.warn("Cannot register new user: " , e);
            return new CommandResponse(Target.JSP,FrontConstant.ERROR);

        }
        return new CommandResponse(Target.JSP,FrontConstant.PROFILE_USER);

    }

    private User getNewUserFromRequest(HttpServletRequest request) {
        String email = request.getParameter("emailAddress");
        String password = request.getParameter("secondPassword");
        String entity = request.getParameter("entity");
        String role = "user";
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String company = request.getParameter("company");
        long TIN = Long.parseLong(request.getParameter("TIN"));
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String numberOfBuilding = request.getParameter("numberOfBuilding");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setEntity(entity);
        user.setRole(role);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCompany(company);
        user.setTIN(TIN);
        user.setCity(city);
        user.setStreet(street);
        user.setNumberOfBuilding(numberOfBuilding);
        return user;
    }
}*/
