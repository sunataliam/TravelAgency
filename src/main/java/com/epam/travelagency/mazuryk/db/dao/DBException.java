package com.epam.travelagency.mazuryk.db.dao;

public class DBException extends Exception{

    public DBException(String message, Exception e) {
        super(message,e);
    }

}
