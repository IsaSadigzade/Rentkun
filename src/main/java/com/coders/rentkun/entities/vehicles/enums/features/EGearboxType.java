package com.coders.rentkun.entities.vehicles.enums.features;

import lombok.Getter;

@Getter
public enum EGearboxType {
    AUTOMATIC("Automatic"),
    CVT("CVT"),
    MANUAL("Manual"),
    SEMI_AUTOMATIC("Semi-Automatic");

    private final String displayName;

    EGearboxType(String displayName) {
        this.displayName = displayName;
    }
}

