package com.coders.rentkun.dtos.vehicles.requests;

import com.coders.rentkun.entities.vehicles.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleDetailsRequestDto {
    private String city;
    private String country;
    private String plateNumber;
    private String numberOfSeats;
    private String distance;
    private String color;
    private String year;
    private String description;
}
