package com.epam.travelagency.mazuryk.db.dao;

import com.epam.travelagency.mazuryk.db.entity.User;


public interface UserDAO extends EntityDAO<User> {

    User getByEmail(String email) throws DBException;

}
