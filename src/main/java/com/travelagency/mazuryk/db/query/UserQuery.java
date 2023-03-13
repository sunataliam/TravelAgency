package com.travelagency.mazuryk.db.query;

public abstract class UserQuery {

    public static final String INSERT_USER = "INSERT INTO user (fk_role,name,surname,email,password,date_birth," +
            "phone_number,blocked) VALUES (?,?,?,?,?,?,?,?)";
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    public static final String UPDATE_USER = "UPDATE user SET fk_role=?, name=?, surname=?, email=?, password=?, " +
            "date_birth=?, phone_number=?, blocked=? WHERE id=?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

    // are you sure that we need it?
    public static final String UPDATE_USER_FK_ROLE = "UPDATE user SET fk_role = ? WHERE id = ?";
    public static final String UPDATE_USER_NAME = "UPDATE user SET name = ? WHERE id = ?";
    public static final String UPDATE_USER_SURNAME = "UPDATE user SET surname = ? WHERE id = ?";
    public static final String UPDATE_USER_PASSWORD = "UPDATE user SET password = ? WHERE id = ?";
    public static final String UPDATE_USER_DATE_BIRTH = "UPDATE user SET date_birth = ? WHERE id = ?";
    public static final String UPDATE_USER_EMAIL = "UPDATE user SET email = ? WHERE id = ?";
    public static final String UPDATE_USER_PHONE_NUMBER = "UPDATE user SET phone_number = ? WHERE id = ?";
    public static final String UPDATE_USER_BLOCKED = "UPDATE user SET blocked = ? WHERE id = ?";

    public static final String SELECT_USER_BY_FK_ROLE = "SELECT * FROM user WHERE fk_role = ?";
    public static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name = ?";
    public static final String SELECT_USER_BY_SURNAME = "SELECT * FROM user WHERE surname = ?";
    public static final String SELECT_USER_BY_DATE_BIRTH = "SELECT * FROM user WHERE date_birth = ?";
    public static final String SELECT_USER_BY_PHONE_NUMBER = "SELECT * FROM user WHERE phone_number = ?";
    public static final String SELECT_USER_BY_BLOCKED = "SELECT * FROM user WHERE blocked = ?";

    // USER ROLE
    public static final String ADD_USER_ROLE = "INSERT INTO user_role (name) VALUES (?)";
    public static final String UPDATE_USER_ROLE = "UPDATE user_role SET name = ? WHERE id = ?";
    public static final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE id = ?";
    public static final String SELECT_ALL_USER_ROLES = "SELECT * FROM user_role";
    public static final String SELECT_USER_ROLE_BY_ID = "SELECT * FROM user_role WHERE id = ?";

}
