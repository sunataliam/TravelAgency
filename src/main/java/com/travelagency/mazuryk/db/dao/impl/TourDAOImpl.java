package com.travelagency.mazuryk.db.dao.impl;

import com.travelagency.mazuryk.db.dao.DBException;
import com.travelagency.mazuryk.db.dao.ConnectionFactory;
import com.travelagency.mazuryk.db.dao.TourDAO;
import com.travelagency.mazuryk.db.entity.Tour;
import com.travelagency.mazuryk.db.enums.HotelType;
import com.travelagency.mazuryk.db.enums.TourType;
import com.travelagency.mazuryk.db.query.TourQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDAOImpl implements TourDAO {

    private static final Logger logger = LogManager.getLogger(TourDAOImpl.class);

    @Override
    public void create(Tour tour) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            addTour(connection,tour);
            connection.commit();
            logger.info("Tour was created successfully with name: " + tour.getName());
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to insert new tour with name: " + tour.getName(), e);
            throw new DBException("Cannot insert new tour", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }

    private void addTour(Connection connection, Tour tour) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(TourQuery.INSERT_TOUR,Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            statement.setString(++i, tour.getName());
            statement.setInt(++i, tour.getTourType().ordinal() + 1);
            statement.setInt(++i, tour.getDuration());
            statement.setString(++i, tour.getDescription());
            statement.setDouble(++i, tour.getPrice());
            statement.setInt(++i, tour.getMembers());
            statement.setInt(++i, tour.getHotelType().ordinal() + 1);
            statement.setDouble(++i, tour.getDiscount());
            statement.setBoolean(++i, tour.isHot());
            int execute = statement.executeUpdate();
            if (execute >= 1) {
                try (ResultSet set = statement.getGeneratedKeys()) {
                    if (set.next()) {
                        tour.setId(set.getInt(1));
                    }
                }
            }
        } catch (SQLException e){
            logger.warn("Failed to add new tour with name: " + tour.getName(), e);
            throw new DBException("Cannot add new tour" , e);
        }
    }

    @Override
    public Tour get(long id) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(TourQuery.SELECT_TOUR_BY_ID)){
            int i = 0;
            statement.setString(++i, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                logger.info("Tour with id " + id + " was found");
                return mapTour(resultSet);
            }
        }
        catch (SQLException e) {
            logger.warn("Failed to get tour with id: " + id, e);
            throw new DBException("Cannot get tour by id", e);
        }
        return null;
    }

    @Override
    public List<Tour> getAll() throws DBException {
        try(Connection connection = ConnectionFactory.getConnection(true);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(TourQuery.SELECT_ALL_TOURS)){
            List<Tour> tours = new ArrayList<>();
            while (resultSet.next()){
                Tour tour = mapTour(resultSet);
                tours.add(tour);
            }
            return tours;
        } catch (SQLException e){
            logger.warn("Failed to get a list of all tours", e);
            throw new DBException("Cannot select all tours",e);
        }
    }

    private Tour mapTour(ResultSet resultSet) throws DBException {
        try {
            Tour tour = new Tour();
            tour.setId(resultSet.getInt("id"));
            tour.setName(resultSet.getString("name"));
            tour.setTourType(TourType.values()[resultSet.getInt("fk_tour_type") - 1]);
            tour.setDuration(resultSet.getInt("duration"));
            tour.setDescription(resultSet.getString("description"));
            tour.setPrice(resultSet.getInt("price"));
            tour.setMembers(resultSet.getInt("members"));
            tour.setHotelType(HotelType.values()[resultSet.getInt("fk_hotel_type") - 1]);
            tour.setDiscount(resultSet.getInt("discount"));
            tour.setHot(resultSet.getBoolean("hot"));
            return tour;
        } catch (SQLException e) {
            throw new DBException("Cannot map tour",e);
        }
    }

    @Override
    public void update(Tour tour) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(TourQuery.UPDATE_TOUR, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setString(++i,tour.getName());
            statement.setInt(++i,tour.getTourType().ordinal() + 1);
            statement.setInt(++i,tour.getDuration());
            statement.setString(++i,tour.getDescription());
            statement.setDouble(++i,tour.getPrice());
            statement.setInt(++i,tour.getMembers());
            statement.setInt(++i,tour.getHotelType().ordinal() + 1);
            statement.setDouble(++i,tour.getDiscount());
            statement.setBoolean(++i,tour.isHot());
            statement.setInt(++i, tour.getId());
            statement.executeUpdate();
            connection.commit();
            logger.info("Tour with name "+ tour.getName() +" was updated successfully");
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to update tour with name: " + tour.getName(), e);
            throw new DBException("Cannot update tour", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void delete(Tour tour) throws DBException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(TourQuery.DELETE_TOUR, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setInt(++i, tour.getId());
            int execute = statement.executeUpdate();
            if (execute > 1) {
                try (ResultSet set = statement.getGeneratedKeys()) {
                    if (set.next()) {
                        tour.setId(set.getInt(1));
                        connection.commit();
                        logger.info("Tour with name "+ tour.getName() +" was deleted successfully");
                    }
                }
            }
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to delete tour with name: " + tour.getName(), e);
            throw new DBException("Cannot delete tour", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }
}
