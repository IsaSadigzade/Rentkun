package com.coders.rentkun.dtos.common.converter;

import com.coders.rentkun.dtos.common.request.AddNewAdvertRequest;
import com.coders.rentkun.dtos.common.request.UpdateAdvertRequest;
import com.coders.rentkun.dtos.common.request.UpdateAdvertTimeRequest;
import com.coders.rentkun.dtos.common.response.AdvertiserResponse;
import com.coders.rentkun.dtos.vehicles.converts.VehicleDtoConverter;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.entities.common.Advertiser;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.vehicles.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class AdvertiserDtoConverter {
    private final VehicleDtoConverter vehicleDtoConverter;

    public AdvertiserDtoConverter(VehicleDtoConverter vehicleDtoConverter) {
        this.vehicleDtoConverter = vehicleDtoConverter;
    }

    public Advertiser convertToEntity(AddNewAdvertRequest request, User advertingUser, Vehicle foundVehicle) {
        Advertiser advertiser = new Advertiser();
        advertiser.setAdvertActive(true);
        advertiser.setVehicleActive(true);
        advertiser.setVehicleRented(false);
        advertiser.onCreate();
        advertiser.setUser(advertingUser);
        advertiser.setVehicle(foundVehicle);
        advertiser.setPrice(request.getPrice());
        advertiser.setAvailableFromDate(request.getAvailableFromDate());
        advertiser.addAdditionalDays(request.getAdditionalDays());
        return advertiser;
    }

    public Advertiser convertToEntity(Advertiser foundAdvert, UpdateAdvertRequest request, Vehicle updatedVehicle) {
        foundAdvert.onUpdate();
        foundAdvert.setVehicle(updatedVehicle);
        foundAdvert.setPrice(request.getPrice());
        return foundAdvert;
    }

    public Advertiser convertToEntity(Advertiser foundAdvert, UpdateAdvertTimeRequest request) {
        foundAdvert.onUpdate();
        foundAdvert.setAvailableFromDate(request.getAvailableFromDate());
        foundAdvert.addAdditionalDays(request.getAdditionalDays());
        return foundAdvert;
    }


    public AdvertiserResponse convertToResponse(Advertiser advertiser) {
        AdvertiserResponse advertiserResponse = new AdvertiserResponse();
        advertiserResponse.setId(advertiser.getId());
        advertiserResponse.setVehicleResponse(vehicleDtoConverter.convertToResponse(advertiser.getVehicle()));
        advertiserResponse.setPrice(advertiser.getPrice());
        advertiserResponse.setAvailableFromDate(advertiser.getAvailableFromDate());
        advertiserResponse.setAvailableToDate(advertiser.getAvailableToDate());
        advertiserResponse.setUsername(advertiser.getUser().getUserInfos().getFirstName() + " " + advertiser.getUser().getUserInfos().getLastName());
        return advertiserResponse;
    }

    public AdvertiserResponse convertToResponse(Advertiser advertiser, VehicleResponseDto responseDto) {
        AdvertiserResponse advertiserResponse = new AdvertiserResponse();
        advertiserResponse.setId(advertiser.getId());
        advertiserResponse.setUsername(advertiser.getUser().getUserInfos().getFirstName() + " " + advertiser.getUser().getUserInfos().getLastName());
        advertiserResponse.setVehicleResponse(responseDto);
        advertiserResponse.setPrice(advertiser.getPrice());
        advertiserResponse.setAvailableFromDate(advertiser.getAvailableFromDate());
        advertiserResponse.setAvailableToDate(advertiser.getAvailableToDate());
        return advertiserResponse;
    }
}
