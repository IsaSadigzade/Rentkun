package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum LandRover {
    Defender("Defender"), Discovery("Discovery"), Discovery_Sport("Discovery Sport"), Freelander("Freelander"),
    Range_Rover_Evoque("Range Rover Evoque"), Range_Rover_Sport("Range Rover Sport"), RR_Velar("RR Velar"),
    Range_Rover("Range Rover");

    private final String model;

    LandRover(String model) {
        this.model = model;
    }

}
