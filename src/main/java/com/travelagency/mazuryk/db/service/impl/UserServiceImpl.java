package com.travelagency.mazuryk.db.service.impl;

import com.travelagency.mazuryk.db.dao.DBException;
import com.travelagency.mazuryk.db.dao.UserDAO;
import com.travelagency.mazuryk.db.entity.User;
import com.travelagency.mazuryk.db.dao.impl.UserDAOImpl;
import com.travelagency.mazuryk.db.enums.UserRole;
import com.travelagency.mazuryk.db.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;

public class UserServiceImpl implements UserService {

    public UserDAO userDAO;

    @Override
    public List<User> getAll() throws DBException {
        return userDAO.getAll();
    }

    @Override
    public User get(Long id) throws DBException {
        return userDAO.get(id);
    }

    @Override
    public User getByEmail(String email) throws DBException {
        return userDAO.getByEmail(email);
    }

    @Override
    public void add(User user) throws DBException {
        userDAO.create(user);
    }

    @Override
    public void update(User user) throws DBException {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) throws DBException {
        userDAO.delete(user);
    }

    @Override
    public User login(String email, String password) throws SQLException, IOException {
        try {
            UserDAO userDAO = new UserDAOImpl();
            User user = userDAO.getByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }

        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String date_of_birth = request.getParameter("date_of_birth");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        if (password.equals(confirm_password)) {
            User user = new User();
            user.setRole(UserRole.USER);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            user.setDateBirth(Date.valueOf(date_of_birth));
            user.setPhoneNumber(phone_number);
            user.setBlocked(false);
            return user;
        } else
            return null;
    }

    @Override
    public boolean checkIfEmailUnique(User user) {
        try {
            UserDAO userDAO = new UserDAOImpl();
            if (userDAO.getByEmail(user.getEmail()) != null) {
                return false;
            }
            return true;
        } catch (DBException e) {
            e.printStackTrace();
        }
        return true;
    }
}
