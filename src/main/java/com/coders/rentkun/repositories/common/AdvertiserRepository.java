package com.coders.rentkun.repositories.common;

import com.coders.rentkun.entities.common.Advertiser;
import com.coders.rentkun.entities.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {
    @Query("SELECT v FROM Vehicle v JOIN v.advertiser a WHERE a.user.id = :userId")
    List<Vehicle> findVehiclesByUserId(@Param("userId") Long userId);

    Advertiser findByVehicleId(Long vehicleId);
}

