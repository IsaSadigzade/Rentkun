package com.coders.rentkun.entities.vehicles.enums.features;

import lombok.Getter;

@Getter
public enum EFuelType {
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    GASOLINE("Gasoline"),
    HYBRID("Hybrid"),
    HYDROGEN("Hydrogen");

    private final String fuelTypeName;

    EFuelType(String fuelTypeName) {
        this.fuelTypeName = fuelTypeName;
    }
}
