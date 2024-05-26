package com.coders.rentkun.services;

import com.coders.rentkun.dtos.common.converter.AdvertiserDtoConverter;
import com.coders.rentkun.dtos.common.request.AddNewAdvertRequest;
import com.coders.rentkun.dtos.common.request.UpdateAdvertRequest;
import com.coders.rentkun.dtos.common.request.UpdateAdvertTimeRequest;
import com.coders.rentkun.dtos.common.response.AdvertiserResponse;
import com.coders.rentkun.dtos.vehicles.converts.VehicleDtoConverter;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.entities.common.Advertiser;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.entities.vehicles.Vehicle;
import com.coders.rentkun.repositories.AdvertiserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertiserService {
    private final EntityManager entityManager;
    private final AdvertiserRepository advertiserRepository;
    private final VehicleService vehicleService;
    private final AdvertiserDtoConverter advertiserDtoConverter;
    private final VehicleDtoConverter vehicleDtoConverter;

    public AdvertiserService(EntityManager entityManager, AdvertiserRepository advertiserRepository, VehicleService vehicleService, AdvertiserDtoConverter advertiserDtoConverter, VehicleDtoConverter vehicleDtoConverter) {
        this.entityManager = entityManager;
        this.advertiserRepository = advertiserRepository;
        this.vehicleService = vehicleService;
        this.advertiserDtoConverter = advertiserDtoConverter;
        this.vehicleDtoConverter = vehicleDtoConverter;
    }

    @Transactional
    public AdvertiserResponse advertNewVehicle(User loggedUser, AddNewAdvertRequest addNewAdvertRequest) {
        loggedUserCheck(loggedUser);
        User attachedUser = entityManager.merge(loggedUser);

        Vehicle savedVehicle = vehicleService.saveForAdvertVehicle(addNewAdvertRequest.getCreateVehicleRequest());
        Vehicle foundVehicle = vehicleService.findByVehicleId(savedVehicle.getId());

        Advertiser advertiser = advertiserDtoConverter.convertToEntity(addNewAdvertRequest, attachedUser, foundVehicle);
        Advertiser savedAdvertiser = advertiserRepository.save(advertiser);

        return advertiserDtoConverter.convertToResponse(savedAdvertiser);
    }

    public List<VehicleResponseDto> getAllAdvertisersVehicles(Long userId) {
        List<Vehicle> vehicles = advertiserRepository.findVehiclesByUserId(userId);
        return vehicles.stream()
                .map(vehicleDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    public AdvertiserResponse updateAdvert(Long advertId, User loggedUser, UpdateAdvertRequest updateAdvertRequest) {
        loggedUserCheck(loggedUser);
        Advertiser advert = findById(advertId);

        Vehicle foundVehicle = vehicleService.findByVehicleId(advert.getVehicle().getId());
        Vehicle updatedVehicle = vehicleService.updateAdvertedVehicle(foundVehicle.getId(), updateAdvertRequest.getUpdateVehicleRequestDto());
        return advertiserDtoConverter.convertToResponse(
                advertiserRepository.save(
                        advertiserDtoConverter.convertToEntity(
                                advert,
                                updateAdvertRequest,
                                updatedVehicle
                        )
                )
        );
    }

    public AdvertiserResponse updateAdvertTime(Long advertId, User loggedUser, UpdateAdvertTimeRequest updateAdvertTimeRequest) {
        loggedUserCheck(loggedUser);
        Advertiser advert = findById(advertId);

        return advertiserDtoConverter.convertToResponse(
                advertiserRepository.save(
                        advertiserDtoConverter.convertToEntity(
                                advert,
                                updateAdvertTimeRequest
                        )
                )
        );
    }

    private Advertiser findById(Long advertId) {
        return advertiserRepository.findById(advertId)
                .orElseThrow(() -> new IllegalStateException("Advertiser not found"));
    }

    private void loggedUserCheck(User loggedUser) {
        if (loggedUser == null) {
            throw new IllegalStateException("User not authenticated");
        }
    }

}
