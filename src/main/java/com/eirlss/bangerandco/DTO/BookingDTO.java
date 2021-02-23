package com.eirlss.bangerandco.DTO;

import com.eirlss.bangerandco.Model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO
{
    private String bookDate;
    private String endBookDate;
    private String startTime;
    private String endBookTime;
    private String status;
    private double totalPrice;

    private User user;
    private Vehicle vehicle;
    private Client client;
    private Equipment equipment;
    private Booking booking;

    private List<Equipment> equipmentList;
    private int clientID;
    private long vehicleID;
    private long equipmentID;
    private long bookingID;
    private String equipmentName;
}
