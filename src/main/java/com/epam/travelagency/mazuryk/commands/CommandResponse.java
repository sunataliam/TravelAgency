package com.epam.travelagency.mazuryk.commands;

import com.epam.travelagency.mazuryk.db.enums.Target;

public class CommandResponse {
    private Target target;
    private String path;

    public CommandResponse(Target target, String path) {
        this.target = target;
        this.path = path;
    }

    public Target getTarget() {
        return target;
    }

    public String getPath() {
        return path;
    }
}
