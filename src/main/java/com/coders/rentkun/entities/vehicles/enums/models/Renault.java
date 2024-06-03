package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Renault {
    _12("12"), _12_Toros("12 Toros"), _19("19"), _5("5"), Captur("Captur"), Clio("Clio"), Dokker("Dokker"),
    Duster("Duster"), Fluence("Fluence"), Grand_Scenic("Grand Scenic"), K_480_P8x4("K 480 P8x4"), Kadjar("Kadjar"),
    Kangoo("Kangoo"), Kaptur("Kaptur"), Koleos("Koleos"), Laguna("Laguna"), Logan("Logan"), Magnum("Magnum"),
    Master("Master"), Megane("Megane"), Megane_Scenic("Megane Scenic"), Modus("Modus"), Premium_460("Premium 460"),
    Sandero("Sandero"), Sandero_Stepway("Sandero Stepway"), Scenic("Scenic"), Symbol("Symbol"), Talisman("Talisman"),
    Tondar("Tondar");

    private final String model;

    Renault(String model) {
        this.model = model;
    }
}
