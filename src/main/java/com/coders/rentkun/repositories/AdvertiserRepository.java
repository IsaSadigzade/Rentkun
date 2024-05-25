package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.common.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {
//    @Query("SELECT a FROM Advertiser a JOIN FETCH a.user u LEFT JOIN FETCH a.vehicles v WHERE a.id = :id")
//    Optional<Advertiser> findByIdWithUserAndVehicles(@Param("id") Long id);
}

