package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.common.request.AddNewAdvertRequest;
import com.coders.rentkun.dtos.common.request.UpdateAdvertRequest;
import com.coders.rentkun.dtos.common.request.UpdateAdvertTimeRequest;
import com.coders.rentkun.dtos.common.response.AdvertiserResponse;
import com.coders.rentkun.dtos.vehicles.responses.VehicleResponseDto;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.services.AdvertiserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/advertiser")
public class AdvertiserController {
    private final AdvertiserService advertiserService;

    public AdvertiserController(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @PostMapping
    public AdvertiserResponse advertNewVehicle(@RequestBody AddNewAdvertRequest addNewAdvertRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return advertiserService.advertNewVehicle(loggedUser, addNewAdvertRequest);
    }

    @GetMapping("/adverts")
    public List<VehicleResponseDto> getAllAdvertisersVehicles() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return advertiserService.getAllAdvertisersVehicles(loggedUser.getId());
    }

    @PutMapping("/update-advert/{advertId}")
    public AdvertiserResponse updateAdvert(@PathVariable Long advertId, @RequestBody UpdateAdvertRequest updateAdvertRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return advertiserService.updateAdvert(advertId, loggedUser, updateAdvertRequest);
    }

    @PutMapping("/update-advert-time/{advertId}")
    public AdvertiserResponse updateAdvertTime(@PathVariable Long advertId, @RequestBody UpdateAdvertTimeRequest updateAdvertTimeRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return advertiserService.updateAdvertTime(advertId, loggedUser, updateAdvertTimeRequest);
    }
}
