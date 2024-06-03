package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum RollsRoyce {
    Cullinan("Cullinan"),
    Ghost("Ghost"),
    Phantom("Phantom"),
    Wraith("Wraith");

    private final String model;

    RollsRoyce(String model) {
        this.model = model;
    }
}
