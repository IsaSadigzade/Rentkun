package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.vehicles.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, Long> {
}
