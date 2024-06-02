package com.coders.rentkun.services.common;

import com.coders.rentkun.dtos.common.converter.RenterDtoConverter;
import com.coders.rentkun.dtos.common.request.RenterRequest;
import com.coders.rentkun.dtos.common.response.RenterResponse;
import com.coders.rentkun.entities.common.Advertiser;
import com.coders.rentkun.entities.common.Renter;
import com.coders.rentkun.entities.common.enums.RentalStatus;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.repositories.common.RenterRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenterService {
    private final RenterRepository renterRepository;
    private final EntityManager entityManager;
    private final AdvertiserService advertiserService;
    private final RenterDtoConverter renterDtoConverter;

    public RenterService(RenterRepository renterRepository, EntityManager entityManager, AdvertiserService advertiserService, RenterDtoConverter renterDtoConverter) {
        this.renterRepository = renterRepository;
        this.entityManager = entityManager;
        this.advertiserService = advertiserService;
        this.renterDtoConverter = renterDtoConverter;
    }

    @Transactional
    public RenterResponse saveRenter(User loggedUser, Long advertId, RenterRequest renterRequest) {
        loggedUserCheck(loggedUser);
        User attachedUser = entityManager.merge(loggedUser);
        Advertiser advert = advertiserService.findById(advertId);

        if (advert.isVehicleRented()) {
            throw new IllegalStateException("Vehicle is already rented.");
        }

        return renterDtoConverter.convertToDto(
                renterRepository.save(
                        renterDtoConverter.convertToEntity(
                                attachedUser,
                                advert,
                                renterRequest
                        )
                )
        );
    }

    public List<RenterResponse> getAllRenters() {
        List<Renter> renters = renterRepository.findAll();
        checkRentalStatusForRents(renters);
        return renters.stream()
                .map(renterDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<RenterResponse> getRentsByUserId(Long userId) {
        List<Renter> rents = renterRepository.findByUserId(userId);
        checkRentalStatusForRents(rents);
        return rents.stream()
                .map(renterDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public void cancelRent(Long rentId) {
        Renter renter = findRenter(rentId);
        renter.setRentalStatus(RentalStatus.CANCELED);
        renterRepository.save(renter);
    }

    private void loggedUserCheck(User loggedUser) {
        if (loggedUser == null) {
            throw new IllegalStateException("User not authenticated");
        }
    }

    private Renter findRenter(Long rentId) {
        return renterRepository.findById(rentId)
                .orElseThrow(() -> new RuntimeException("Rent not found"));
    }

    private void checkRentalStatusForRents(List<Renter> rents) {
        for (Renter rent : rents) {
            if (rent.getEndDate().equals(LocalDateTime.now()) || rent.getEndDate().isBefore(LocalDateTime.now())) {
                renterDtoConverter.updateRentalStatus(rent);
                renterRepository.save(rent);
            }
            if (rent.getRentalStatus() == RentalStatus.FINISHED || rent.getRentalStatus() == RentalStatus.CANCELED) {
                renterDtoConverter.updateAdvertRentalStatus(rent);
                renterRepository.save(rent);
            }
        }
    }
}