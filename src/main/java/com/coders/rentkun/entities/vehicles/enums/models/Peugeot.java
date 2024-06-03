package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Peugeot {
    _2008("2008"), e_2008("e-2008"), _206("206"), _206_CC("206 CC"), _207("207"), _3008("3008"), _301("301"),
    _307("307"), _308("308"), _405("405"), _406("406"), _407("407"), _408("408"), _5008("5008"), _508("508"),
    _607("607"), _807("807"), Bipper("Bipper"), Boxer("Boxer"), Expert("Expert"), Khazar_406("Khazar 406"),
    Pars("Pars"), Partner("Partner"), Rifter("Rifter"), Traveller("Traveller");

    private final String model;

    Peugeot(String model) {
        this.model = model;
    }
}
