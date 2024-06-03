package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Audi {
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4"),
    A4_allroad("A4 allroad"),
    A5("A5"),
    A5_Sportback("A5 Sportback"),
    A6("A6"),
    A6_allroad("A6 allroad"),
    A7("A7"),
    A7_Sportback("A7 Sportback"),
    A8("A8"),
    Q2("Q2"),
    Q2_e_tron("Q2 e-tron"),
    Q3("Q3"), Q5("Q5"),
    Q5_Sportback("Q5 Sportback"),
    Q5_e_tron("Q5 e-tron"),
    Q7("Q7"), Q8("Q8"),
    Q8_Sportback_e_tron("Q8 Sportback e-tron"),
    Q8_e_tron("Q8 e-tron"),
    R8("R8"),
    RS3("RS3"),
    RS4("RS4"),
    RS7("RS7"),
    S3("S3"), S4("S4"),
    S5("S5"), S6("S6"),
    S7("S7"), S8("S8"),
    TT("TT"),
    e_tron("e-tron"),
    _100("100"),
    _80("80"),
    _90("90");

    private final String model;

    Audi(String model) {
        this.model = model;
    }
}
