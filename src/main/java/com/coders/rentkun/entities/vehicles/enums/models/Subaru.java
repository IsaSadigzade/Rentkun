package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Subaru {
    B9_Tribeca("B9 Tribeca"), BRZ("BRZ"), Forester("Forester"), Impreza("Impreza"), Impreza_WRX("Impreza WRX"),
    Legacy("Legacy"), Outback("Outback"), Tribeca("Tribeca"), XV("XV");

    private final String model;

    Subaru(String model) {
        this.model = model;
    }
}
