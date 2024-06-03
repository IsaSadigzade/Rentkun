package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Jeep {
    Cherokee("Cherokee"), Commander("Commander"), Compass("Compass"), Gladiator("Gladiator"),
    Grand_Cherokee("Grand Cherokee"), Patriot("Patriot"), Renegade("Renegade"), Wagoneer("Wagoneer"),
    Wrangler("Wrangler");

    private final String model;

    Jeep(String model) {
        this.model = model;
    }
}
