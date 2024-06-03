package com.coders.rentkun.entities.vehicles.enums.features;

import lombok.Getter;

@Getter
public enum EVehicleType {
    Compact("Compact"),
    Convertible("Convertible"),
    Coupes("Coupes"),
    Electric("Electric"),
    Hatchback("Hatchback"),
    Hybrid("Hybrid"),
    Luxury("Luxury"),
    Micro("Micro"),
    Muscle("Muscle"),
    OffRoad("Off-road"),
    Pickup("Pickup"),
    Sedan("Sedan"),
    Sport("Sport"),
    Station("Station"),
    SUV("SUV"),
    Van("Van"),
    Vintage("Vintage");

    private final String typeName;

    EVehicleType(String typeName) {
        this.typeName = typeName;
    }
}
