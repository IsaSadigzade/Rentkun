package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum AlfaRomeo {
    GTA_Coupe("GTA Coupe"),
    Giulia("Giulia"),
    Giulietta("Giulietta"),
    Stelvio("Stelvio"),
    MiTo("MiTo");

    private final String model;

    AlfaRomeo(String model) {
        this.model = model;
    }
}
