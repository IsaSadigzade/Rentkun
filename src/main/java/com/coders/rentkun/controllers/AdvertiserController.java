package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.common.request.AdvertiserRequest;
import com.coders.rentkun.dtos.common.response.AdvertiserResponse;
import com.coders.rentkun.dtos.vehicles.requests.CreateVehicleRequestDto;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.services.AdvertiserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/advertiser")
public class AdvertiserController {
    private final AdvertiserService advertiserService;

    public AdvertiserController(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @PostMapping
    public AdvertiserResponse advertNewVehicle(@RequestBody AdvertiserRequest advertiserRequest) {
        User advertingUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return advertiserService.advertNewVehicle(advertiserRequest, advertingUser);
    }
}
