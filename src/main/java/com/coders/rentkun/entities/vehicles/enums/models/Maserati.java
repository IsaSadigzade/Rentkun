package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Maserati {
    Ghibli("Ghibli"),
    GranCabrio("Gran Cabrio"),
    GranTurismo("Gran Turismo"),
    GranTurismo_S("Gran Turismo S"),
    Grecale("Grecale"),
    Levante("Levante"),
    Quattroporte("Quattroporte");

    private final String model;

    Maserati(String model) {
        this.model = model;
    }
}
