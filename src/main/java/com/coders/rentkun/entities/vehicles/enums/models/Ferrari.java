package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Ferrari {
    _458_Italia("458 Italia"), _488_GT("_488 GT"), California("California"), Portofino("Portofino"),
    SF90_Stradale("SF90 Stradale"), F8_Tributo("F8 Tributo"), Roma("Roma");

    private final String model;

    Ferrari(String model) {
        this.model = model;
    }
}
