package com.coders.rentkun.dtos.common.converter;

import com.coders.rentkun.dtos.common.request.AdvertiserRequest;
import com.coders.rentkun.dtos.common.response.AdvertiserResponse;
import com.coders.rentkun.dtos.vehicles.converts.VehicleDtoConverter;
import com.coders.rentkun.entities.common.Advertiser;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.vehicles.Vehicle;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdvertiserDtoConverter {
    private final VehicleDtoConverter vehicleDtoConverter;

    public AdvertiserDtoConverter(VehicleDtoConverter vehicleDtoConverter) {
        this.vehicleDtoConverter = vehicleDtoConverter;
    }

    public Advertiser convertToEntity (User advertingUser, Vehicle foundVehicle) {
        Advertiser advertiser = new Advertiser();
        advertiser.setAdvertActive(true);
        advertiser.setCreatedAt(LocalDateTime.now());
        advertiser.setUpdatedAt(LocalDateTime.now());
        advertiser.setUser(advertingUser);
        advertiser.setVehicle(foundVehicle);
        return advertiser;
    }

    public AdvertiserResponse convertToResponse (Advertiser advertiser) {
        AdvertiserResponse advertiserResponse = new AdvertiserResponse();
        advertiserResponse.setId(advertiser.getId());
        advertiserResponse.setVehicleResponse(vehicleDtoConverter.convertToResponse(advertiser.getVehicle()));
        return advertiserResponse;
    }
}
