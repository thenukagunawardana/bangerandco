package com.eirlss.bangerandco.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name =  "client", uniqueConstraints =
@UniqueConstraint(columnNames = "email"))
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String status;

    private String driversLicense;

    private int age;

    private int userID;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Booking.class)
    private List<Booking> bookingList;
}