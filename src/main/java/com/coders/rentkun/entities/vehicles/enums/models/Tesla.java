package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Tesla {
    Model_S("Model S"),
    Model_3("Model 3"),
    Model_X("Model X"),
    Model_Y("Model Y");

    private final String model;

    Tesla(String model) {
        this.model = model;
    }
}
