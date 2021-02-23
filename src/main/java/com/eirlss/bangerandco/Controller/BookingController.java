package com.eirlss.bangerandco.Controller;


import com.eirlss.bangerandco.DTO.BookingDTO;
import com.eirlss.bangerandco.Model.Client;
import com.eirlss.bangerandco.Model.Equipment;
import com.eirlss.bangerandco.Model.User;
import com.eirlss.bangerandco.Model.Vehicle;
import com.eirlss.bangerandco.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/booking")
@Controller
public class BookingController
{
    @Autowired
    UserServiceImpl userService;
    @Autowired
    VehicleService vehicleService;

    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    EquipmentServiceImpl equipmentService;

    @Autowired
    BookingServiceImpl bookingService;


    @GetMapping("/createBooking/{id}")
    public String createBooking(@PathVariable(value = "id") int vehicleID, Model model) {

        try {

            Vehicle vehicle = vehicleService.getVehicleByID(vehicleID);

            List<Equipment> equipmentList = equipmentService.getAllEquipment();

            String email = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails)
            {
                email = ((UserDetails) principal).getUsername();
            } else
                {
                email = principal.toString();
                }
            User user = userService.searchByEmail(email);
            Client client = clientService.getClientByID(user.getId());

            if(vehicle != null) {

                BookingDTO bookingDTO = new BookingDTO();

                bookingDTO.setVehicleID(vehicle.getId());
                bookingDTO.setVehicle(vehicle);
                bookingDTO.setClientID(client.getId());
                bookingDTO.setClient(client);

                model.addAttribute("booking", bookingDTO);
                model.addAttribute("equipmentList", equipmentList);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "createBooking";
    }

    @PostMapping("/addBooking")
    public String addBooking(@ModelAttribute("booking") BookingDTO bookingDTO,
                                  @RequestParam(value = "equipmentID", required = false) List<Long> equipmentList) {

        bookingService.saveBooking(equipmentList, bookingDTO);

//        if(CalculateReservationTotal.pickUpDateIsBeforeDropOffDate(reservationReg.getReservation()) == true) {
//            List<Equipment> newEquipmentList = new ArrayList<>();
//            showEquipment(equipmentList, newEquipmentList);

//            return "redirect:/renter/loadHome";
//        } else
//            {
//            return "BookingError";
        return "";
        }

    }


