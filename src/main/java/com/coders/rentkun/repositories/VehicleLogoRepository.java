package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.vehicles.VehicleLogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleLogoRepository extends JpaRepository<VehicleLogo, Long> {
    Optional<VehicleLogo> findByName(String filename);
}
