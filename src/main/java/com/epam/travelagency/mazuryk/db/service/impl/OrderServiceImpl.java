package com.epam.travelagency.mazuryk.db.service.impl;

import com.epam.travelagency.mazuryk.db.dao.DBException;
import com.epam.travelagency.mazuryk.db.dao.OrderDAO;
import com.epam.travelagency.mazuryk.db.entity.Order;
import com.epam.travelagency.mazuryk.db.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    @Override
    public List<Order> getAll() throws DBException {
        return orderDAO.getAll();
    }

    @Override
    public Order get(Long id) throws DBException {
        return orderDAO.get(id);
    }

    @Override
    public void add(Order order) throws DBException {
        orderDAO.create(order);
    }

    @Override
    public void update(Order order) throws DBException {
        orderDAO.update(order);
    }

    @Override
    public void delete(Order order) throws DBException {
        orderDAO.delete(order);
    }
}
