package com.coders.rentkun.dtos.common.converter;

import com.coders.rentkun.dtos.common.request.RenterRequest;
import com.coders.rentkun.dtos.common.response.RenterResponse;
import com.coders.rentkun.entities.common.Advertiser;
import com.coders.rentkun.entities.common.Renter;
import com.coders.rentkun.entities.common.enums.RentalStatus;
import com.coders.rentkun.entities.users.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RenterDtoConverter {
    public Renter convertToEntity(User loggedUser, Advertiser advert, RenterRequest request) {
        Renter renter = new Renter();
        renter.setRentedDays(request.getRentedDays());
        renter.setPricePerDay(advert.getPrice());
        renter.setTotalRentalPrice(advert.getPrice().multiply(BigDecimal.valueOf(request.getRentedDays())));
        renter.addRentedDays(request.getRentedDays());
        renter.setRentalStatus(RentalStatus.ON_GOING);
        renter.setUser(loggedUser);
        renter.setVehicle(advert.getVehicle());
        advert.setVehicleRented(true);
        return renter;
    }

    public RenterResponse convertToDto(Renter renter) {
        return new RenterResponse(
                renter.getId(),
                renter.getUser().getUserInfos().getFirstName() + " " + renter.getUser().getUserInfos().getLastName(),
                renter.getVehicle().getVehicleBrand().getName() + " " + renter.getVehicle().getVehicleModel().getName(),
                renter.getVehicle().getVehicleDetails().getPlateNumber(),
                renter.getRentalStatus()
        );
    }

    public void updateRentalStatus(Renter rent) {
        rent.setRentalStatus(RentalStatus.FINISHED);
        rent.getVehicle().getAdvertiser().setVehicleRented(false);
    }

    public void updateAdvertRentalStatus(Renter rent) {
        rent.getVehicle().getAdvertiser().setVehicleRented(false);
    }
}

