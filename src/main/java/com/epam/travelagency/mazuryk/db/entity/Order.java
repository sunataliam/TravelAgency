package com.epam.travelagency.mazuryk.db.entity;

import com.epam.travelagency.mazuryk.db.enums.OrderStatus;

import java.util.Date;
import java.util.Objects;

public class Order {

    private int id;
    private Tour tour;
    private User user;
    private OrderStatus status;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(tour, order.tour) && Objects.equals(user, order.user) && Objects.equals(status, order.status) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tour, user, status, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tour=" + tour +
                ", user=" + user +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
