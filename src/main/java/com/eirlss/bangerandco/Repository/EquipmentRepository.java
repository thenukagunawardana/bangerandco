package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Integer>
{

}
