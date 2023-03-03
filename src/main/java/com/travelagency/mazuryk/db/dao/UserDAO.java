package com.travelagency.mazuryk.db.dao;

import com.travelagency.mazuryk.db.entity.User;


public interface UserDAO extends EntityDAO<User> {

    User getByEmail(String email) throws DBException;

}
