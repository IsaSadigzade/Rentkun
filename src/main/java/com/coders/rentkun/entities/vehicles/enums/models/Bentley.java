package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Bentley {
    Bentayga("Bentayga"),
    Continental("Continental"),
    Continental_Flying_Spur("Continental Flying Spur"),
    Continental_GT("Continental GT"),
    Flying_Spur("Flying Spur"),
    Mulsanne("Mulsanne");

    private final String model;

    Bentley(String model) {
        this.model = model;
    }
}
