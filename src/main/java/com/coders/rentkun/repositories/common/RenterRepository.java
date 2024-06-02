package com.coders.rentkun.repositories.common;

import com.coders.rentkun.entities.common.Renter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RenterRepository extends JpaRepository<Renter, Long> {
    List<Renter> findByUserId(Long userId);
}
