package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Citroen {
    Ami_EV("Ami EV"), Berlingo("Berlingo"), C3("C3"), C3_Aircross("C3 Aircross"), C3_Picasso("C3 Picasso"), C4("C4"),
    C4_Grand_Picasso("C4 Grand Picasso"), C4_Cactus("C4 Cactus"), C5("C5"), C5_Aircross("C5 Aircross"), DS4("DS4"),
    Jumpy("Jumpy");

    private final String model;

    Citroen(String model) {
        this.model = model;
    }
}
