package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Mazda {
    _2("2"),
    _3("3"),
    _5("5"),
    _6("6"),
    CX_30("CX-30"),
    CX_5("CX-5"),
    CX_60("CX-60"),
    CX_7("CX-7"),
    CX_9("CX-9"),
    CX_90("CX-90");

    private final String model;

    Mazda(String model) {
        this.model = model;
    }
}
