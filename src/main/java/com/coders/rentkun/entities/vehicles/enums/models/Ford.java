package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Ford {
    Bronco("Bronco"), Bronco_Sport("Bronco Sport"), C_MAX("C-MAX"), Cargo("Cargo"), Courier("Courier"),
    Ecosport("Ecosport"), Edge("Edge"), Escape("Escape"), Escort("Escort"), Explorer("Explorer"), F_150("F-150"),
    F_450("F-450"), Fiesta("Fiesta"), Fiesta_ST("Fiesta ST"), Focus("Focus"), Fusion("Fusion"), Galaxy("Galaxy"),
    Ka("Ka");

    private final String model;

    Ford(String model) {
        this.model = model;
    }
}
