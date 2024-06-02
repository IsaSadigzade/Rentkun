package com.coders.rentkun.entities.common.enums;

public enum PaymentStatus {
    PAID("PAID"),
    PENDING("PENDING"),
    FAILED("FAILED");

    public final String status;

    PaymentStatus(String status) {
        this.status = status;
    }
}

