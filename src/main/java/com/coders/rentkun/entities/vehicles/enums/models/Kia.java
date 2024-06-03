package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Kia {
    Avella("Avella"), Besta("Besta"), Bongo("Bongo"), Cadenza("Cadenza"), Carens("Carens"), Carnival("Carnival"),
    Ceed("Ceed"), Cerato("Cerato"), Cerato_Koup("Cerato Koup"), Clarus("Clarus"), EV5("EV5"), EV6("EV6"), EV9("EV9"),
    Forte("Forte"), K2700("K2700"), K3("K3"), K4("K4"), K5("K5"), K7("K7"), K8("K8"), Magentis("Magentis"), Mohave("Mohave"),
    Morning("Morning"), Niro("Niro"), Optima("Optima"), Picanto("Picanto"), Pregio("Pregio"), Pride("Pride"),
    ProCeed("ProCeed"), Quoris("Quoris"), Ray("Ray"), Rio("Rio"), Rio_X_Line("Rio X-Line"), Seltos("Seltos"),
    Sephia("Sephia"), Sorento("Sorento"), Soul("Soul"), Sportage("Sportage"), Stinger("Stinger"), Stonic("Stonic");

    private final String model;

    Kia(String model) {
        this.model = model;
    }
}
