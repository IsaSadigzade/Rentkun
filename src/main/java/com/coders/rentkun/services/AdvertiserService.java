package com.coders.rentkun.services;

import com.coders.rentkun.dtos.common.converter.AdvertiserDtoConverter;
import com.coders.rentkun.dtos.common.request.AdvertiserRequest;
import com.coders.rentkun.dtos.common.response.AdvertiserResponse;
import com.coders.rentkun.dtos.vehicles.converts.VehicleDtoConverter;
import com.coders.rentkun.entities.common.Advertiser;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.vehicles.Vehicle;
import com.coders.rentkun.repositories.AdvertiserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserService {
    private final EntityManager entityManager;
    private final AdvertiserRepository advertiserRepository;
    private final VehicleService vehicleService;
    private final AdvertiserDtoConverter advertiserDtoConverter;

    public AdvertiserService(EntityManager entityManager, AdvertiserRepository advertiserRepository, VehicleService vehicleService, AdvertiserDtoConverter advertiserDtoConverter) {
        this.entityManager = entityManager;
        this.advertiserRepository = advertiserRepository;
        this.vehicleService = vehicleService;
        this.advertiserDtoConverter = advertiserDtoConverter;
    }

    @Transactional
    public AdvertiserResponse advertNewVehicle(AdvertiserRequest advertiserRequest, User advertingUser) {
        if (advertingUser == null) {
            throw new IllegalStateException("User not authenticated");
        }

        User attachedUser = entityManager.merge(advertingUser);

        Vehicle savedVehicle = vehicleService.saveForAdvertVehicle(advertiserRequest.getCreateVehicleRequest());
        Vehicle foundVehicle = vehicleService.findByVehicleId(savedVehicle.getId());

        Advertiser advertiser = advertiserDtoConverter.convertToEntity(attachedUser, foundVehicle);
        Advertiser savedAdvertiser = advertiserRepository.save(advertiser);

        return advertiserDtoConverter.convertToResponse(savedAdvertiser);
    }
}
