package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum MercedesMaybach {
    EQS_680_4MATIC_SUV("EQS 680 4MATIC SUV"),
    GLS_600_4MATIC("GLS 600 4MATIC"),
    S_400("S 400"),
    S_450("S 450"),
    S_450_4MATIC("S 450 4MATIC"),
    S_480("S 480"),
    S_500("S 500"),
    S_560("S 560"),
    S_580_4MATIC("S 580 4MATIC");

    private final String model;

    MercedesMaybach(String model) {
        this.model = model;
    }
}
