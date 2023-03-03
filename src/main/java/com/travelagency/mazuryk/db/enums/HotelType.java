package com.travelagency.mazuryk.db.enums;

public enum HotelType {
    ONE_STAR,
    TWO_STARS,
    THREE_STARS,
    FOUR_STARS,
    FIVE_STARS,
    HOSTEL;

    public String getName() {
        return name().toLowerCase();
    }
}

