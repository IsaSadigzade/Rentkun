package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Chevrolet {
    Aveo("Aveo"),
    Blazer("Blazer"),
    Camaro("Camaro"),
    Captiva("Captiva"),
    Cavalier("Cavalier"),
    Cobalt("Cobalt"),
    Colorado("Colorado"),
    Cruze("Cruze"),
    Damas("Damas"),
    Epica("Epica"),
    Equinox("Equinox"),
    Evanda("Evanda"),
    Express("Express"),
    Kalos("Kalos"),
    Labo("Labo"),
    Lacetti("Lacetti"),
    Lumina("Lumina"),
    Malibu("Malibu"),
    Matiz("Matiz"),
    Monza("Monza"),
    Nexia("Nexia"),
    Niva("Niva"),
    Nubira("Nubira"),
    Onix("Onix"),
    Orlando("Orlando"),
    Rezzo("Rezzo"),
    Sonic("Sonic"),
    Spark("Spark"),
    Suburban("Suburban"),
    Tahoe("Tahoe"),
    Tracker("Tracker"),
    TrailBlazer("TrailBlazer"),
    Traverse("Traverse"),
    Trax("Trax"),
    Volt("Volt");

    private final String model;

    Chevrolet(String model) {
        this.model = model;
    }
}
