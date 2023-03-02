package com.epam.travelagency.mazuryk.db.service.impl;

import com.epam.travelagency.mazuryk.db.dao.DBException;
import com.epam.travelagency.mazuryk.db.dao.TourDAO;

import com.epam.travelagency.mazuryk.db.entity.Tour;
import com.epam.travelagency.mazuryk.db.service.TourService;

import java.util.List;

public class TourServiceImpl implements TourService {

    private TourDAO tourDAO;

    @Override
    public List<Tour> getAll() throws DBException {
        return tourDAO.getAll();
    }

    @Override
    public Tour get(Long id) throws DBException {
        return tourDAO.get(id);
    }

    @Override
    public void add(Tour tour) throws DBException {
        tourDAO.create(tour);
    }

    @Override
    public void update(Tour tour) throws DBException {
        tourDAO.update(tour);
    }

    @Override
    public void delete(Tour tour) throws DBException {
        tourDAO.delete(tour);
    }
}
