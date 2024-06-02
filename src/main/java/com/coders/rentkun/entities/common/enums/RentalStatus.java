package com.coders.rentkun.entities.common.enums;

public enum RentalStatus {
    ON_GOING("ON GOING"),
    FINISHED("FINISHED"),
    CANCELED("CANCELED");

    public final String status;

    RentalStatus(String status) {
        this.status = status;
    }
}
