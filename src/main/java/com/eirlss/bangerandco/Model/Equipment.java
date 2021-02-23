package com.eirlss.bangerandco.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="equipment")
public class Equipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private Double price;

    @ManyToMany(mappedBy = "equipmentList")
    private List<Booking> bookingList;
}
