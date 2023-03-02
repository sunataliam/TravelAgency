package com.epam.travelagency.mazuryk.db.query;

public abstract class TourQuery {

    // TOUR
    public static final String INSERT_TOUR = "INSERT INTO tour (name, fk_tour_type, duration, description, price, members, " +
            "fk_hotel_type, discount, hot) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_ALL_TOURS = "SELECT * FROM tour";
    public static final String SELECT_TOUR_BY_ID = "SELECT * FROM tour WHERE id = ?";
    public static final String UPDATE_TOUR = "UPDATE tour SET name=?, fk_tour_type=?, duration=?, description=?, price=?, " +
            "members=?, fk_hotel_type=?, discount=?, hot=? WHERE id = ?";
    public static final String DELETE_TOUR = "DELETE FROM tour WHERE id = ?";

    // are you sure that we need it?
    public static final String UPDATE_TOUR_NAME = "UPDATE tour SET name = ? WHERE id = ?";
    public static final String UPDATE_TOUR_FK_TOUR_TYPE = "UPDATE tour SET fk_tour_type = ? WHERE id = ?";
    public static final String UPDATE_TOUR_DURATION = "UPDATE tour SET duration = ? WHERE id = ?";
    public static final String UPDATE_TOUR_DESCRIPTION = "UPDATE tour SET description = ? WHERE id = ?";
    public static final String UPDATE_TOUR_PRICE = "UPDATE tour SET price = ? WHERE id = ?";
    public static final String UPDATE_TOUR_MEMBERS = "UPDATE tour SET members = ? WHERE id = ?";
    public static final String UPDATE_TOUR_FK_HOTEL_TYPE = "UPDATE tour SET fk_hotel_type = ? WHERE id = ?";
    public static final String UPDATE_TOUR_DISCOUNT = "UPDATE tour SET discount = ? WHERE id = ?";
    public static final String UPDATE_TOUR_HOT = "UPDATE tour SET hot = ? WHERE id = ?";
    public static final String SELECT_TOUR_BY_NAME = "SELECT * FROM tour WHERE name = ?";
    public static final String SELECT_TOUR_BY_FK_TOUR_TYPE = "SELECT * FROM tour WHERE fk_tour_type = ?";
    public static final String SELECT_TOUR_BY_DURATION = "SELECT * FROM tour WHERE duration = ?";
    public static final String SELECT_TOUR_BY_DESCRIPTION = "SELECT * FROM tour WHERE description = ?";
    public static final String SELECT_TOUR_BY_PRICE = "SELECT * FROM tour WHERE price = ?";
    public static final String SELECT_TOUR_BY_MEMBERS = "SELECT * FROM tour WHERE members = ?";
    public static final String SELECT_TOUR_BY_FK_HOTEL_TYPE = "SELECT * FROM tour WHERE fk_hotel_type = ?";
    public static final String SELECT_TOUR_BY_DISCOUNT = "SELECT * FROM tour WHERE discount = ?";
    public static final String SELECT_TOUR_BY_HOT = "SELECT * FROM tour WHERE hot = ?";

    // TOUR TYPE
    public static final String ADD_TOUR_TYPE = "INSERT INTO tour_type (name) VALUES (?)";
    public static final String UPDATE_TOUR_TYPE = "UPDATE tour_type SET name = ? WHERE id = ?";
    public static final String DELETE_TOUR_TYPE = "DELETE FROM tour_type WHERE id = ?";
    public static final String SELECT_ALL_TOUR_TYPES = "SELECT * FROM tour_type";
    public static final String SELECT_TOUR_TYPE_BY_ID = "SELECT * FROM tour_type WHERE id = ?";

    // HOTEL TYPE
    public static final String ADD_HOTEL_TYPE = "INSERT INTO hotel_type (name) VALUES (?)";
    public static final String UPDATE_HOTEL_TYPE = "UPDATE hotel_type SET name = ? WHERE id = ?";
    public static final String DELETE_HOTEL_TYPE = "DELETE FROM hotel_type WHERE id = ?";
    public static final String SELECT_ALL_HOTEL_TYPES = "SELECT * FROM hotel_type";
    public static final String SELECT_HOTEL_TYPE_BY_ID = "SELECT * FROM hotel_type WHERE id = ?";
}
