package com.epam.travelagency.mazuryk.db.enums;


public enum OrderStatus {
    REGISTERED,
    PAID,
    CANCELED;

    public String getName() {
        return name().toLowerCase();
    }

}
