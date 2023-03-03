package com.travelagency.mazuryk.db.service;

import com.travelagency.mazuryk.db.dao.DBException;

import java.util.List;

public interface EntityService<T>  {
    public List<T> getAll() throws DBException;
    public T get(Long id) throws DBException;
    public void add(T t) throws DBException;
    public void update(T t) throws DBException;
    public void delete(T t) throws DBException;
}
