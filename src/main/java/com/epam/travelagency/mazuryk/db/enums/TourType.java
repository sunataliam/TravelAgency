package com.epam.travelagency.mazuryk.db.enums;

public enum TourType {
    REST,
    EXCURSION,
    SHOPPING;

    public String getName() {
        return name().toLowerCase();
    }

}
