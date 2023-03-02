package com.epam.travelagency.mazuryk.db.dao.impl;

import com.epam.travelagency.mazuryk.db.dao.DBException;
import com.epam.travelagency.mazuryk.db.dao.ConnectionFactory;
import com.epam.travelagency.mazuryk.db.dao.OrderDAO;
import com.epam.travelagency.mazuryk.db.entity.Order;
import com.epam.travelagency.mazuryk.db.enums.OrderStatus;
import com.epam.travelagency.mazuryk.db.query.OrderQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAOImpl implements OrderDAO {

    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class);

    @Override
    public void create(Order order) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(OrderQuery.INSERT_ORDER,Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setInt(++i,order.getTour().getId());
            statement.setInt(++i,order.getUser().getId());
            statement.setInt(++i,order.getStatus().ordinal() + 1);
            statement.setDate(++i,Date.valueOf(String.valueOf(order.getDate())));
            int execute = statement.executeUpdate();
            if(execute >= 1){
                try (ResultSet set = statement.getGeneratedKeys()){
                    if(set.next()){
                        order.setId(set.getInt(1));
                    }
                }
            }
            connection.commit();
            logger.info("Order was created successfully with id: " + order.getId());
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to insert new order", e);
            throw new DBException("Cannot insert new order", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public Order get(long id) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(OrderQuery.SELECT_ORDER_BY_ID)){
            int i = 0;
            statement.setString(++i, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                logger.info("Order with id " + id + " was found");
                return mapOrder(resultSet);
            }
        }
        catch (SQLException e) {
            logger.warn("Failed to get order with id: " + id, e);
            throw new DBException("Cannot get order by id", e);
        }
        return null;
    }

    @Override
    public List<Order> getAll() throws DBException {
        try(Connection connection = ConnectionFactory.getConnection(true);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(OrderQuery.SELECT_ALL_ORDERS)){
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()){
                Order order = mapOrder(resultSet);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e ){
            logger.warn("Failed to get a list of all orders", e);
            throw new DBException("Cannot select all orders",e);
        }
    }

    private Order mapOrder(ResultSet resultSet) throws DBException {
        try {
            Order order = new Order();
            TourDAOImpl tour = new TourDAOImpl();
            UserDAOImpl user = new UserDAOImpl();
            order.setId(resultSet.getInt("id"));
            order.setTour(tour.get(resultSet.getInt("fk_tour")));
            order.setUser(user.get(resultSet.getInt("fk_user")));
            order.setStatus(OrderStatus.values()[resultSet.getInt("fk_status") - 1]);
            order.setDate(resultSet.getDate("date"));
            return order;
        } catch (SQLException e) {
            throw new DBException("Cannot map order",e);
        }
    }

    @Override
    public void update(Order order) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(OrderQuery.UPDATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setInt(++i, order.getTour().getId());
            statement.setInt(++i, order.getUser().getId());
            statement.setInt(++i, order.getStatus().ordinal() + 1);
            statement.setDate(++i, Date.valueOf(String.valueOf(order.getDate())));
            statement.setInt(++i, order.getId());
            statement.executeUpdate();
            connection.commit();
            logger.info("Order with id "+ order.getId() +" was updated successfully");
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to update order with id: " + order.getId(), e);
            throw new DBException("Cannot update order", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void delete(Order order) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(OrderQuery.DELETE_ORDER, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setInt(++i, order.getId());
            int execute = statement.executeUpdate();
            if (execute > 1) {
                try (ResultSet set = statement.getGeneratedKeys()) {
                    if (set.next()) {
                        order.setId(set.getInt(1));
                        connection.commit();
                        logger.info("Order with id "+ order.getId() +" was deleted successfully");
                    }
                }
            }
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to delete order with id: " + order.getId(), e);
            throw new DBException("Cannot delete order", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }
}
