package com.travelagency.mazuryk.commands;

import com.travelagency.mazuryk.db.enums.Target;

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
