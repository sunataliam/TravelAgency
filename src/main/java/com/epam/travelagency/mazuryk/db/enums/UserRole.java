package com.epam.travelagency.mazuryk.db.enums;

public enum UserRole {
    USER,
    MANAGER,
    ADMIN;

    public String getName() {
        return name().toLowerCase();
    }

}
