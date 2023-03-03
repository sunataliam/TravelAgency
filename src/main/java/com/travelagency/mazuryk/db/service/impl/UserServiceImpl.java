package com.travelagency.mazuryk.db.service.impl;

import com.travelagency.mazuryk.db.dao.DBException;
import com.travelagency.mazuryk.db.dao.UserDAO;
import com.travelagency.mazuryk.db.entity.User;
import com.travelagency.mazuryk.db.dao.impl.UserDAOImpl;
import com.travelagency.mazuryk.db.service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        try  {
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
}
