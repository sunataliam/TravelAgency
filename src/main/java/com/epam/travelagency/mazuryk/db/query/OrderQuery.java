package com.epam.travelagency.mazuryk.db.query;

public abstract class OrderQuery {

    public static final String INSERT_ORDER = "INSERT INTO order (fk_tour, fk_user, fk_status, date) VALUES (?,?,?,?)";
    public static final String SELECT_ALL_ORDERS = "SELECT * FROM order";
    public static final String SELECT_ORDER_BY_ID = "SELECT * FROM order WHERE id = ?";
    public static final String UPDATE_ORDER = "UPDATE ORDER SET fk_tour=?, fk_user=?, fk_status=?, date=? WHERE id=?";
    public static final String DELETE_ORDER = "DELETE FROM order WHERE id = ?";

    // are you sure that we need it?
    public static final String UPDATE_ORDER_FK_TOUR = "UPDATE order SET fk_tour = ? WHERE id = ?";
    public static final String UPDATE_ORDER_FK_USER = "UPDATE order SET fk_user = ? WHERE id = ?";
    public static final String UPDATE_ORDER_FK_STATUS = "UPDATE order SET fk_status = ? WHERE id = ?";
    public static final String UPDATE_ORDER_DATE = "UPDATE order SET date = ? WHERE id = ?";
    public static final String SELECT_ORDER_BY_FK_TOUR = "SELECT * FROM order WHERE fk_tour = ?";
    public static final String SELECT_ORDER_BY_FK_USER = "SELECT * FROM order WHERE fk_user = ?";
    public static final String SELECT_ORDER_BY_FK_STATUS = "SELECT * FROM order WHERE fk_status = ?";
    public static final String SELECT_ORDER_BY_DATE = "SELECT * FROM order WHERE date = ?";

    // ORDER STATUS
    public static final String ADD_ORDER_STATUS = "INSERT INTO order_status (name) VALUES (?)";
    public static final String UPDATE_ORDER_STATUS = "UPDATE order_status SET name = ? WHERE id = ?";
    public static final String DELETE_ORDER_STATUS = "DELETE FROM order_status WHERE id = ?";
    public static final String SELECT_ALL_ORDER_STATUSES = "SELECT * FROM order_status";
    public static final String SELECT_ORDER_STATUS_BY_ID = "SELECT * FROM order_status WHERE id = ?";
}
