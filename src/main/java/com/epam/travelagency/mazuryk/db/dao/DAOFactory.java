package com.epam.travelagency.mazuryk.db.dao;
import com.epam.travelagency.mazuryk.db.dao.impl.OrderDAOImpl;
import com.epam.travelagency.mazuryk.db.dao.impl.TourDAOImpl;
import com.epam.travelagency.mazuryk.db.dao.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 A factory for obtaining DAO instances for different entities.
 */
public class DAOFactory {
    private static final Logger logger = LogManager.getLogger(DAOFactory.class);
    private static DAOFactory instance;
    /**
     Private constructor to ensure that instances are only created via the getInstance() method.*/
    private DAOFactory(){
        logger.info("Created new DAOFactory instance");
    }
    /**
     Returns the singleton instance of the DAOFactory class.
     If an instance has not been created yet, creates a new one.
     @return the singleton instance of DAOFactory
     @throws SQLException if a database access error occurs
     */
    public static synchronized DAOFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    /**
     Returns a new UserDAO instance.
     @return a new UserDAO instance
     */
    public UserDAO getUserDAO() {
        logger.debug("Getting UserDAO instance");
        return new UserDAOImpl();
    }
    /**
     Returns a new TourDAO instance.
     @return a new TourDAO instance
     */
    public TourDAO getTourDAO() {
        logger.debug("Getting TourDAO instance");
        return new TourDAOImpl();
    }
    /**
     Returns a new OrderDAO instance.
     @return a new OrderDAO instance
     */
    public OrderDAO getOrderDAO() {
        logger.debug("Getting OrderDAO instance");
        return new OrderDAOImpl();
    }
}
