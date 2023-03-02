package com.epam.travelagency.mazuryk.db.entity;

import com.epam.travelagency.mazuryk.db.enums.HotelType;
import com.epam.travelagency.mazuryk.db.enums.TourType;

import java.util.Objects;

public class Tour {

    private int id;
    private String name;
    private TourType tourType;
    private int duration;
    private String description;
    private double price;
    private int members;
    private HotelType hotelType;
    private double discount;
    private boolean hot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id == tour.id && Double.compare(tour.price, price) == 0 && members == tour.members && Double.compare(tour.discount, discount) == 0 && hot == tour.hot && Objects.equals(name, tour.name) && Objects.equals(tourType, tour.tourType) && duration == tour.duration && Objects.equals(description, tour.description) && Objects.equals(hotelType, tour.hotelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tourType, duration, description, price, members, hotelType, discount, hot);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tourType=" + tourType +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", members=" + members +
                ", hotelType=" + hotelType +
                ", discount=" + discount +
                ", hot=" + hot +
                '}';
    }
}
