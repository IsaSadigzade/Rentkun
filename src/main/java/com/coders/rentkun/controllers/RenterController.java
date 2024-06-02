package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.common.request.RenterRequest;
import com.coders.rentkun.dtos.common.response.RenterResponse;
import com.coders.rentkun.entities.users.User;
import com.coders.rentkun.services.common.RenterService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rent")
public class RenterController {
    private final RenterService renterService;

    public RenterController(RenterService renterService) {
        this.renterService = renterService;
    }

    @PostMapping("/{advertId}")
    public RenterResponse createRent(@PathVariable Long advertId, @RequestBody RenterRequest renterRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return renterService.saveRenter(loggedUser, advertId, renterRequest);
    }

    @GetMapping
    public List<RenterResponse> getAllRents() {
        return renterService.getAllRenters();
    }

    @GetMapping("/{userId}")
    public List<RenterResponse> getRentsByUserId(@PathVariable Long userId) {
        return renterService.getRentsByUserId(userId);
    }

    @PatchMapping("/{rentId}/cancel")
    public void cancelRent(@PathVariable Long rentId) {
        renterService.cancelRent(rentId);
    }
}
