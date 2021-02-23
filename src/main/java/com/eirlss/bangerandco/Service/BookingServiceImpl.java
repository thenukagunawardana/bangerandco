package com.eirlss.bangerandco.Service;


import com.eirlss.bangerandco.DTO.BookingDTO;
import com.eirlss.bangerandco.Model.Booking;
import com.eirlss.bangerandco.Model.Client;
import com.eirlss.bangerandco.Model.Equipment;
import com.eirlss.bangerandco.Model.Vehicle;
import com.eirlss.bangerandco.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service

public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    VehicleService vehicleService;

    @Autowired
    ClientServiceImpl clientService;

    @Override
    public boolean saveBooking(@RequestParam(value = "equipmentID", required = false) List<Long> equipmentList,
                               BookingDTO bookingDTO) {

        try {
            Vehicle vehicle = vehicleService.getVehicleByID(bookingDTO.getVehicleID());
            Client client = clientService.getClientByID(bookingDTO.getClientID());
            Booking booking = new Booking();
            List<Booking> bookingList = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startBookDate = LocalDate.parse(bookingDTO.getBookDate(), formatter);
            LocalDate stopBookDate = LocalDate.parse(bookingDTO.getEndBookDate(), formatter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime bookTime = LocalTime.parse(bookingDTO.getStartTime(), timeFormatter);
            LocalTime endTime = LocalTime.parse(bookingDTO.getEndBookTime(), timeFormatter);

            booking.setBookDate(startBookDate);
            booking.setEndBookDate(stopBookDate);
            booking.setBookTime(bookTime);
            booking.setEndBookTime(endTime);
            booking.setStatus("Active");

            bookingDTO.setBooking(booking);

            List<Booking> getAllVehicleBookings = vehicle.getBookingList();

//            if (CalculateBooking.startDateIsBeforeEndDate(booking)) {
//
//                if (!overlapsWithExistingBooking(booking, getAllCarBookings)) {
//
            List<Equipment> equipmentArrayList = new ArrayList<>();
//
//                    calculateEquipment(equipmentList, newEquipmentList);
//
//                    CalculateBooking.calculateDifferenceInDays(booking);
//
//                    double totalBookingPrice = CalculateBooking.calculateTotalBooking(booking, car, newEquipmentList);
//                    booking.setTotalPrice(totalBookingPrice);

            bookingList = client.getBookingList();
            bookingList.add(booking);
            client.setBookingList(bookingList);
            booking.setVehicle(vehicle);
            booking.setClient(client);
            booking.setEquipmentList(equipmentArrayList);
            bookingList = vehicle.getBookingList();
            bookingList.add(booking);
            vehicle.setBookingList(bookingList);

            bookingRepository.save(booking);
            return true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

