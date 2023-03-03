package com.travelagency.mazuryk.db.dao;

import java.util.List;

public interface EntityDAO<T> {

    void create (T t) throws DBException;

    T get(long id) throws DBException;

    List<T> getAll() throws DBException;

    void update(T t) throws DBException;

    void delete(T t) throws DBException;
}
