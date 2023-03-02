package com.epam.travelagency.mazuryk.db.service;

import com.epam.travelagency.mazuryk.db.dao.DBException;
import com.epam.travelagency.mazuryk.db.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserService extends EntityService<User> {
    public User getByEmail(String email) throws DBException;
    public User login(String email, String password) throws SQLException, IOException;

}
