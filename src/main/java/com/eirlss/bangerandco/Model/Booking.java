package com.eirlss.bangerandco.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking implements Serializable
{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endBookDate;

    private LocalTime  bookTime;

    private LocalTime endBookTime;

    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carBookingID")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientBookingID")
    private Client client;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "booking_equipmentID",
            joinColumns = @JoinColumn(name = "bookingID"),
            inverseJoinColumns = @JoinColumn(name = "equipmentID"))
    private List<Equipment> equipmentList;

}
