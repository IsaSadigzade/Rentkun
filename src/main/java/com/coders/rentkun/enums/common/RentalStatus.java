package com.coders.rentkun.enums.common;

public enum RentalStatus {
    ON_GOING("ON GOING"),
    FINISHED("FINISHED"),
    CANCELED("CANCELED");

    public final String status;

    RentalStatus(String status) {
        this.status = status;
    }
}
