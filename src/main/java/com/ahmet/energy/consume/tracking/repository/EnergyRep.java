package com.ahmet.energy.consume.tracking.repository;

import com.ahmet.energy.consume.tracking.entity.Energy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EnergyRep extends JpaRepository<Energy,String> {

    @Query("select e from Energy e where e.id = ?1")
    Optional<Energy> findById(String id);
}
