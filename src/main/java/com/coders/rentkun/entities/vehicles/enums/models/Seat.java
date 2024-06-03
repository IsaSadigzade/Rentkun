package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Seat {
    Altea("Altea"),
    Ateca("Ateca"),
    Exeo("Exeo"),
    Ibiza("Ibiza"),
    Leon("Leon"),
    Tarraco("Tarraco"),
    Toledo("Toledo");

    private final String model;

    Seat(String model) {
        this.model = model;
    }
}
