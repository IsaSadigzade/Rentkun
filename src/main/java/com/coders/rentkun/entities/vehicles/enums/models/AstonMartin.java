package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum AstonMartin {
    DB11("DB11"),
    DBS("DBS"),
    Rapide("Rapide"),
    Valkyrie("Valkyrie"),
    Vantage("Vantage");

    private final String model;

    AstonMartin(String model) {
        this.model = model;
    }
}
