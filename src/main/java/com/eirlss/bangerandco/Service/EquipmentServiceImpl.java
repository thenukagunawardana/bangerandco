package com.eirlss.bangerandco.Service;

import com.eirlss.bangerandco.Model.Equipment;
import com.eirlss.bangerandco.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService
{
    @Autowired
    private EquipmentRepository equipmentRepository;


    @Override
    public void saveEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> getAllEquipment()
    {

        return equipmentRepository.findAll();

    }

}
