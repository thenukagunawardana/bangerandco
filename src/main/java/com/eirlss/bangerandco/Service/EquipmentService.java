package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.Model.Equipment;
import com.eirlss.bangerandco.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EquipmentService
{

    void saveEquipment(Equipment equipment);

    List<Equipment> getAllEquipment();
}
