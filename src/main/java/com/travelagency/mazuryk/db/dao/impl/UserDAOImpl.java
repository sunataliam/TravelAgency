package com.travelagency.mazuryk.db.dao.impl;

import com.travelagency.mazuryk.db.dao.DBException;
import com.travelagency.mazuryk.db.dao.ConnectionFactory;
import com.travelagency.mazuryk.db.dao.UserDAO;
import com.travelagency.mazuryk.db.entity.User;
import com.travelagency.mazuryk.db.enums.UserRole;
import com.travelagency.mazuryk.db.query.UserQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    @Override
    public void create(User user) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            addUser(connection,user);
            connection.commit();
            logger.info("User was created successfully with email: " + user.getEmail());
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to insert new user with email: " + user.getEmail(), e);
            throw new DBException("Cannot insert new user", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }

    private void addUser(Connection connection, User user) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.INSERT_USER, Statement.RETURN_GENERATED_KEYS)){
            int i = 0;
            statement.setInt(++i,user.getRole().ordinal() + 1);
            statement.setString(++i,user.getName());
            statement.setString(++i,user.getSurname());
            statement.setString(++i,user.getEmail());
            statement.setString(++i,user.getPassword());
            statement.setDate(++i,Date.valueOf(String.valueOf(user.getDateBirth())));
            statement.setString(++i,user.getPhoneNumber());
            statement.setBoolean(++i,user.isBlocked());
            int execute = statement.executeUpdate();
            if(execute >= 1){
                try (ResultSet set = statement.getGeneratedKeys()){
                    if(set.next()){
                        user.setId(set.getInt(1));
                    }
                }
            }
        } catch (SQLException e){
            logger.warn("Failed to add new user with email: " + user.getEmail(), e);
            throw new DBException("Cannot add new user" , e);
        }
    }

    @Override
    public User get(long id) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_USER_BY_ID)){
            int i = 0;
            statement.setString(++i, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                logger.info("User with id " + id + " was found");
                return mapUser(resultSet);
            }
        }
        catch (SQLException e) {
            logger.warn("Failed to get user with id: " + id, e);
            throw new DBException("Cannot get user by id", e);
        }
        return null;
    }

    @Override
    public User getByEmail(String email) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_USER_BY_EMAIL)){
            int i = 0;
            statement.setString(++i, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                logger.info("User with the email address " + email + "was found ");
                return mapUser(resultSet);
            }
        }
        catch (SQLException e) {
            logger.warn("Failed to get a user with email: " + email, e);
            throw new DBException("Cannot get user by email", e);
        }
        return null;
    }

    @Override
    public List<User> getAll() throws DBException {
        try(Connection connection = ConnectionFactory.getConnection(true);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(UserQuery.SELECT_ALL_USERS)){
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                User user = mapUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e){
            logger.warn("Failed to get a list of all users", e);
            throw new DBException("Cannot select all users",e);
        }
    }

    private User mapUser(ResultSet resultSet) throws DBException {
        try {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setRole(UserRole.values()[resultSet.getInt("fk_role") - 1]);
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setDateBirth(resultSet.getDate("date_birth"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setBlocked(resultSet.getBoolean("blocked"));
            return user;
        } catch (SQLException e) {
            throw new DBException("Cannot map user",e);
        }
    }

    @Override
    public void update(User user) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setInt(++i,user.getRole().ordinal() + 1);
            statement.setString(++i,user.getName());
            statement.setString(++i,user.getSurname());
            statement.setString(++i,user.getEmail());
            statement.setString(++i,user.getPassword());
            statement.setDate(++i,Date.valueOf(String.valueOf(user.getDateBirth())));
            statement.setString(++i,user.getPhoneNumber());
            statement.setBoolean(++i,user.isBlocked());
            statement.setInt(++i, user.getId());
            statement.executeUpdate();
            connection.commit();
            logger.info("User with email "+ user.getEmail() +" was updated successfully");
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to update user with email: " + user.getEmail(), e);
            throw new DBException("Cannot update user", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void delete(User user) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(UserQuery.DELETE_USER, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setInt(++i, user.getId());
            int execute = statement.executeUpdate();
            if (execute > 1) {
                try (ResultSet set = statement.getGeneratedKeys()) {
                    if (set.next()) {
                        user.setId(set.getInt(1));
                        connection.commit();
                        logger.info("User with email "+ user.getEmail() +" was deleted successfully");
                    }
                }
            }
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to delete user with email: " + user.getEmail(), e);
            throw new DBException("Cannot delete user", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }
}
