package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Mini {
    Convertible("Convertible"),
    Clubman("Clubman"),
    Cooper("Cooper"),
    Countryman("Countryman"),
    One("One");

    private final String model;

    Mini(String model) {
        this.model = model;
    }
}
