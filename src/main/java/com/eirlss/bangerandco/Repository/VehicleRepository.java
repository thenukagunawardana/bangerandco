package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>
{

}

