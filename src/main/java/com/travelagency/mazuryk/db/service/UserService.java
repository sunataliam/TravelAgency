package com.travelagency.mazuryk.db.service;

import com.travelagency.mazuryk.db.dao.DBException;
import com.travelagency.mazuryk.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;

public interface UserService extends EntityService<User> {
    public User getByEmail(String email) throws DBException;
    public User login(String email, String password) throws SQLException, IOException;
    public User getUserFromRequest(HttpServletRequest request);
    public boolean checkIfEmailUnique(User user);

}
