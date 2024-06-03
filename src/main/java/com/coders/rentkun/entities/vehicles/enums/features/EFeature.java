package com.coders.rentkun.entities.vehicles.enums.features;

import lombok.Getter;

@Getter
public enum EFeature {
    AUTOMATIC_HIGH_BEAMS("Automatic High Beams"),
    AUTOMATIC_PARKING_BRAKE("Automatic Parking Brake"),
    DAYTIME_RUNNING_LIGHTS("Daytime Running Lights"),
    ELECTRONIC_STABILITY_CONTROL("Electronic Stability Control"),
    FOG_LIGHTS("Fog Lights"),
    HEADLIGHT_WASHERS("Headlight Washers"),
    HEATED_SEATS("Heated Seats"),
    HDMI_INPUT("HDMI Input"),
    HILL_START_ASSIST("Hill Start Assist"),
    LEATHER_INTERIOR("Leather Interior"),
    MASSAGE_SEATS("Massage Seats"),
    MEMORY_SEATS("Memory Seats"),
    PANORAMIC_ROOF("Panoramic Roof"),
    POWER_ADJUSTABLE_SEATS("Power Adjustable Seats"),
    POWER_TAILGATE("Power Tailgate"),
    RAIN_SENSING_WIPERS("Rain Sensing Wipers"),
    REAR_ENTERTAINMENT_SYSTEM("Rear Entertainment System"),
    REMOTE_START("Remote Start"),
    ROOF_RACK("Roof Rack"),
    SELF_PARKING("Self Parking"),
    SMART_KEY_SYSTEM("Smart Key System"),
    SUNROOF("Sunroof"),
    SURROUND_SOUND_SYSTEM("Surround Sound System"),
    TIRE_PRESSURE_MONITORING_SYSTEM("Tire Pressure Monitoring System"),
    TOUCHSCREEN_DISPLAY("Touchscreen Display"),
    TRACTION_CONTROL("Traction Control"),
    TRAFFIC_SIGN_RECOGNITION("Traffic Sign Recognition"),
    TRAILER_HITCH("Trailer Hitch"),
    TOWING_PACKAGE("Towing Package"),
    USB_PORTS("USB Ports"),
    VENTILATED_SEATS("Ventilated Seats"),
    WIRELESS_PHONE_CHARGER("Wireless Phone Charger");

    private final String featureName;

    EFeature(String featureName) {
        this.featureName = featureName;
    }
}
