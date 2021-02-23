package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>
{
    @Query("SELECT p FROM Vehicle p WHERE p.name LIKE %?1%")
    List<Vehicle> findAll(String keyword);

    @Query("SELECT vehicle from Vehicle vehicle where vehicle.id = ?1")
    Vehicle getByID(long id);
}

