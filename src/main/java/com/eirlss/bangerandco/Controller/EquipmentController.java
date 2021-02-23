package com.eirlss.bangerandco.Controller;

import com.eirlss.bangerandco.Model.Equipment;
import com.eirlss.bangerandco.Service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/equipment")
@Controller
public class EquipmentController
{
    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/loadEquipmentForm")
    public String loadEquipmentForm(Model model) {
        model.addAttribute("equipment", new Equipment());
        return "addEquipment";
    }

    @PostMapping("/saveEquipment")
    public String addEquipment(@ModelAttribute("equipment") Equipment equipment) {
        Equipment addEquipment = new Equipment();
        addEquipment.setId(equipment.getId());
        addEquipment.setName(equipment.getName());
        addEquipment.setDescription(equipment.getDescription());
        addEquipment.setPrice(equipment.getPrice());
        equipmentService.saveEquipment(addEquipment);

        return "redirect:/equipment/loadEquipmentForm?success";
    }

}
